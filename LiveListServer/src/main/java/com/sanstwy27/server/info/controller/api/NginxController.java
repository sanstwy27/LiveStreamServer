package com.sanstwy27.server.info.controller.api;

import com.sanstwy27.server.info.service.NginxStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanstwy27
 * @create 10/1/2020
 */

@RestController
@RequestMapping(path = "${strings.prefix.controller.api}/nginx")
public class NginxController {

    @Autowired
    private NginxStatService nginxStatService;

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return new ResponseEntity<Object>(nginxStatService.getStreamInfos(), HttpStatus.OK);
    }

    @GetMapping("/list/{page}/{offset}")
    public ResponseEntity<Object> list(@PathVariable String page, @PathVariable String offset) {
        return new ResponseEntity<Object>(nginxStatService.getStreamInfos(Integer.valueOf(page), Integer.valueOf(offset)), HttpStatus.OK);
    }
}
