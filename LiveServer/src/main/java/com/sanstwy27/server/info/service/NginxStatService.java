package com.sanstwy27.server.info.service;

import com.sanstwy27.server.info.bean.StreamInfo;
import com.sanstwy27.server.info.config.YmlPropertiesNginxConfig;
import com.sanstwy27.server.info.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sanstwy27
 * @create 10/1/2020
 */

@Service
public class NginxStatService {

    @Autowired
    private YmlPropertiesNginxConfig ymlPropertiesNginxConfig;

    private List<StreamInfo> streamInfoList;

    public Map<String, Object> getStreamInfos(int page, int offset) {
        return MyUtil.packInfo((List<StreamInfo>) MyUtil.getSubList(streamInfoList, page, offset), streamInfoList.size());
    }

    public Map<String, Object> getStreamInfos() {
        return MyUtil.packInfo(streamInfoList, streamInfoList.size());
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 90 * 1000)
    public List<?> parseClients() {
        try {
            // XML response
            String URL = ymlPropertiesNginxConfig.getStatPath();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            // normalize XML response
            doc.getDocumentElement().normalize();

            // read stream list
            NodeList nodeList = doc.getElementsByTagName("stream");

            // create an empty list for streamInfo
            List<StreamInfo> streamInfos = new ArrayList<>();

            // loop all available stream nodes
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    StreamInfo si = new StreamInfo(
                            "id",
                            "name",
                            "account",
                            "title",
                            "language",
                            Integer.parseInt(elem.getElementsByTagName("nclients").item(0).getTextContent()),
                            "avatarUrl",
                            "http://localhost/flv?port=1935&app=hls&stream=" + elem.getElementsByTagName("name").item(0).getTextContent(),
                            ""
                    );
                    streamInfos.add(si);
                }
            }

            // result
            streamInfoList = streamInfos;
            System.out.println(streamInfoList);
            return streamInfos;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
