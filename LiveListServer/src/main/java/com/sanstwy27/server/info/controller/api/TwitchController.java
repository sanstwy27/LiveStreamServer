package com.sanstwy27.server.info.controller.api;

import com.sanstwy27.server.info.service.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanstwy27
 * @create 9/29/2020
 */

@RestController
@RequestMapping(path = "${strings.prefix.controller.api}/twitch")
public class TwitchController {

    @Autowired
    private TwitchService twitchService;

    @GetMapping("/slist/{lang}")
    public ResponseEntity<Object> myStreamInfoList(@PathVariable String lang) {
        return new ResponseEntity<Object>(twitchService.getStreamInfos(lang), HttpStatus.OK);
    }

    @GetMapping("/slist/{lang}/{page}/{offset}")
    public ResponseEntity<?> myStreamInfoList(@PathVariable String lang, @PathVariable String page, @PathVariable String offset) {
        return new ResponseEntity<Object>(twitchService.getStreamInfos(lang, Integer.valueOf(page), Integer.valueOf(offset)), HttpStatus.OK);
    }

    @GetMapping("/tlist/{lang}")
    public ResponseEntity<Object> list(@PathVariable String lang) {
        return new ResponseEntity<Object>(twitchService.getTwitchStreams(lang), HttpStatus.OK);
    }

    @GetMapping("/tlist/{lang}/{page}/{offset}")
    public ResponseEntity<?> list(@PathVariable String lang, @PathVariable String page, @PathVariable String offset) {
        return new ResponseEntity<Object>(twitchService.getTwitchStreams(lang, Integer.valueOf(page), Integer.valueOf(offset)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public void test() {
        twitchService.test();
    }
}
