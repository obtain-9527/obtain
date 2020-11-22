package com.obtain.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author obtain_y
 * @date 2020/11/21 14:55
 * @description com.obtain.study
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class);
    }

    @GetMapping("/api/map")
    public ResponseEntity<Map<String, String>> getMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("yj","yangJie");
        return ResponseEntity.ok(map);
    }
}
