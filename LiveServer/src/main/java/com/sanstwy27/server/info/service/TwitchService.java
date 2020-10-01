package com.sanstwy27.server.info.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanstwy27.server.info.bean.StreamInfo;
import com.sanstwy27.server.info.bean.TwitchChannel;
import com.sanstwy27.server.info.bean.TwitchPOJO;
import com.sanstwy27.server.info.config.YmlPropertiesTwitchConfig;
import com.sanstwy27.server.info.util.MyUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sanstwy27
 * @create 9/29/2020
 */

@Service
public class TwitchService {

    @Autowired
    private YmlPropertiesTwitchConfig ymlPropertiesTwitchConfig;

    static private List<StreamInfo> streamInfoList;
    static private List<TwitchChannel> twitchChannelList;
    private String accessToken;

    @Test
    public void test() {

    }

    public List<StreamInfo> getStreamInfos(int page, int offset) {
        return (List<StreamInfo>) MyUtil.getSubList(streamInfoList, page, offset);
    }

    public List<StreamInfo> getStreamInfos() {
        return streamInfoList;
    }

    public List<TwitchChannel> getTwitchStreams(int page, int offset) {
        return (List<TwitchChannel>) MyUtil.getSubList(twitchChannelList, page, offset);
    }

    public List<TwitchChannel> getTwitchStreams() {
        return twitchChannelList;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 90 * 1000)
    private void scheduleGetStreams() {
        if(ymlPropertiesTwitchConfig == null) {
            System.out.println("oops.. ymlPropertiesTwitchConfig == null");
            return;
        }

        if(accessToken == null || accessToken.equals("")) {
            updateToken();
            if(accessToken == null || accessToken.equals("")) {
                System.out.println("oops.. updateToken() failed");
                return;
            }
        }

        String apiUrl = "https://api.twitch.tv/helix/streams";
        String language = "zh";
        String after = null; // cursor
        System.out.println(apiUrl);

        List<TwitchChannel> tmp = new ArrayList<TwitchChannel>();
        RestTemplate restTemplate = new RestTemplate();

        while(after == null || !after.equals("")) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("language", language)
                    .queryParam("after", after);

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Authorization", String.format("%s %s", "Bearer", accessToken));
            headers.add("Client-Id", ymlPropertiesTwitchConfig.getClientId());
            HttpEntity he = new HttpEntity(null, headers);
            ResponseEntity<String> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, he, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            TwitchPOJO readValue = null;
            try {
                readValue = objectMapper.readValue(
                        exchange.getBody(), new TypeReference<TwitchPOJO>() {
                        }
                );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if(readValue != null && !readValue.getData().isEmpty()) {
                System.out.println(readValue);
                System.out.println(readValue.getData().get(0));
                System.out.println(readValue.getPagination().getCursor());
                tmp.addAll(readValue.getData());
                after = readValue.getPagination().getCursor();
            } else {
                after = "";
            }
        }
        convertTwitchListToMyList(tmp);
        twitchChannelList = tmp;
    }

    @Test
    private void updateToken() {
        String apiUrl = "https://id.twitch.tv/oauth2/token";
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("client_id", ymlPropertiesTwitchConfig.getClientId())
                .queryParam("client_secret", ymlPropertiesTwitchConfig.getClientSecret())
                .queryParam("grant_type", ymlPropertiesTwitchConfig.getGrantType());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity he = new HttpEntity(null, headers);

        ResponseEntity<Map> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, he, Map.class);
        System.out.println(exchange.getStatusCodeValue());
        System.out.println(exchange.getBody());
        accessToken = (String) exchange.getBody().get("access_token");
    }

    private void convertTwitchListToMyList(List<TwitchChannel> src) {
        List<StreamInfo> tmp = new ArrayList<StreamInfo>();
        for(TwitchChannel tc : src) {
            tmp.add(new StreamInfo(tc.getUserName(),
                               tc.getTitle(),
                               tc.getLanguage(),
                               Integer.valueOf(tc.getViewerCount()),
                       "avatarUrl",
                      "streamUrl",
                               tc.getThumbnailUrl()));
        };
        streamInfoList = tmp;
    }
}
