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

    @GetMapping("/slist")
    public ResponseEntity<Object> myStreamInfoList() {
        return new ResponseEntity<Object>(twitchService.getStreamInfos(), HttpStatus.OK);
    }

    @GetMapping("/slist/{page}/{offset}")
    public ResponseEntity<?> myStreamInfoList(@PathVariable String page, @PathVariable String offset) {
        return new ResponseEntity<Object>(twitchService.getStreamInfos(Integer.valueOf(page), Integer.valueOf(offset)), HttpStatus.OK);
    }

    @GetMapping("/tlist")
    public ResponseEntity<Object> list() {
        return new ResponseEntity<Object>(twitchService.getTwitchStreams(), HttpStatus.OK);
    }

    @GetMapping("/tlist/{page}/{offset}")
    public ResponseEntity<?> list(@PathVariable String page, @PathVariable String offset) {
        return new ResponseEntity<Object>(twitchService.getTwitchStreams(Integer.valueOf(page), Integer.valueOf(offset)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public void test() {
        twitchService.test();
    }
}
