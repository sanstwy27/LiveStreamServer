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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sanstwy27
 * @create 9/29/2020
 */

@Service
public class TwitchService {

    final int QUERY_LIMIT = 100;

    @Autowired
    private YmlPropertiesTwitchConfig ymlPropertiesTwitchConfig;

    static private Map<String, List<StreamInfo>> streamInfoMap = new ConcurrentHashMap<String, List<StreamInfo>>();
    static private Map<String, List<TwitchChannel>> twitchChannelMap = new ConcurrentHashMap<String, List<TwitchChannel>>();
    private String accessToken;

    @Test
    public void test() {
    }

    public Map<String, Object> getStreamInfos(String lang, int page, int offset) {
        return MyUtil.packInfo((List<StreamInfo>) MyUtil.getSubList(streamInfoMap.get(lang), page, offset), streamInfoMap.get(lang).size());
    }

    public Map<String, Object> getStreamInfos(String lang) {
        return MyUtil.packInfo(streamInfoMap.get(lang), streamInfoMap.get(lang).size());
    }

    public List<TwitchChannel> getTwitchStreams(String lang, int page, int offset) {
        return (List<TwitchChannel>) MyUtil.getSubList(twitchChannelMap.get(lang), page, offset);
    }

    public List<TwitchChannel> getTwitchStreams(String lang) {
        return twitchChannelMap.get(lang);
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 90 * 1000)
    private void scheduleUpdateZhStreams() {
        updateStreams("zh");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 90 * 1000)
    private void scheduleUpdateJaStreams() {
        updateStreams("ja");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 120 * 1000)
    private void scheduleUpdateKoStreams() {
        updateStreams("ko");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 180 * 1000)
    private void scheduleUpdateEnStreams() {
        updateStreams("en");
    }

    private void updateStreams(String lang) {
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

        String apiUrl = ymlPropertiesTwitchConfig.getApiUrl().getGetstreams();
        String after = null; // cursor
        System.out.println(apiUrl);

        List<TwitchChannel> tmp = new ArrayList<TwitchChannel>();
        RestTemplate restTemplate = new RestTemplate();

        while (after == null || !after.equals("")) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("language", lang.toString())
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
            if (readValue != null && !readValue.getData().isEmpty()) {
                System.out.println(readValue);
                System.out.println(readValue.getData().get(0));
                System.out.println(readValue.getPagination().getCursor());
                tmp.addAll(readValue.getData());
                after = readValue.getPagination().getCursor();
            } else {
                after = "";
            }
        }
        updateUsers(tmp);
        convertTwitchListToMyList(lang, tmp);
        System.out.println("[Twitch] total streams : " + tmp.size() + ", lang: " + lang);
        twitchChannelMap.put(lang, tmp);
    }

    private void updateToken() {
        System.out.println(ymlPropertiesTwitchConfig.getClientId());
        String apiUrl = ymlPropertiesTwitchConfig.getApiUrl().getToken();
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

    private void updateUsers(List<TwitchChannel> list) {
        if(list != null && !list.isEmpty()) {
            String usersApi = ymlPropertiesTwitchConfig.getApiUrl().getUsers();
            RestTemplate restTemplate = new RestTemplate();

            for(int i = 0; i < list.size(); i += QUERY_LIMIT) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(usersApi);
                for(int j = i; j < Math.min(list.size(), i + QUERY_LIMIT); j++) {
                    builder.queryParam("id", list.get(j).getUserId());
                }
                System.out.println(builder.toUriString());

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
                    for(int j = 0; j < readValue.getData().size(); j++) {
                        list.get(i + j).setLogin(readValue.getData().get(j).getLogin());
                        list.get(i + j).setDisplayName(readValue.getData().get(j).getDisplayName());
                        list.get(i + j).setDescription(readValue.getData().get(j).getDescription());
                        list.get(i + j).setProfileImageUrl(readValue.getData().get(j).getProfileImageUrl());
                        list.get(i + j).setOfflineImageUrl(readValue.getData().get(j).getOfflineImageUrl());
                    }
                }
            }
        }
    }

    private void convertTwitchListToMyList(String lang, List<TwitchChannel> src) {
        List<StreamInfo> tmp = new ArrayList<StreamInfo>();
        for(TwitchChannel tc : src) {
            tmp.add(new StreamInfo(
                        tc.getUserId(),
                        tc.getUserName(),
                        tc.getLogin(),
                        tc.getTitle(),
                        tc.getLanguage(),
                        Integer.valueOf(tc.getViewerCount()),
                        tc.getProfileImageUrl(),
                        "https://www.twitch.tv/" + tc.getLogin(),
                        tc.getThumbnailUrl().replace("{width}x{height}", "320x180")));
        };
        streamInfoMap.put(lang, tmp);
    }
}
