package com.sanstwy27.server.info;

import com.sanstwy27.server.info.config.YmlPropertiesTwitchConfig;
import com.sanstwy27.server.info.service.TwitchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = YmlPropertiesTwitchConfig.class)
@SpringBootTest(classes = TwitchService.class)
class LiveListServerApplicationTests {

    @Autowired
    private TwitchService twitchService;

    @Test
    void contextLoads() {
        twitchService.test();
    }



}
