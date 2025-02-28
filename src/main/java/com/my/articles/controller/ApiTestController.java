package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiTestController {
    @GetMapping("/api")
    public String apiFrom() {
        return "/test/api";
    }

//    @RequestBody:json형식을>Object(dto)
//    리턴 타입이 :ResponseEntity< Object전달
    @PostMapping("/apiPostTest")
    public ResponseEntity<String> apiPostTest(@RequestBody LoginDTO dto){
        String info = dto.getUserid()+"/"+dto.getPassword();
        System.out.println("===== info :" + info);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }
}
