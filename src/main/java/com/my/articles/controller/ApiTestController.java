package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiTestController {
    @GetMapping("/api")
    public String apiFrom() {
        return "/test/api";
    }

    //    @RequestBody:json형식을>Object(dto)
//    리턴 타입이 :ResponseEntity< Object전달
    @PostMapping("/apiPostTest")
    public ResponseEntity<String> apiPostTest(@RequestBody LoginDTO dto) {
        String info = dto.getUserid() + "/" + dto.getPassword();
        System.out.println("===== info :" + info);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }

    @PostMapping("/getResponse")
    @ResponseBody
    public LoginDTO postResponse() {
        return new LoginDTO("안유진", "1233");
    }

    @PostMapping("apiPostArrayTest")
    public ResponseEntity<Map<String, String>> apiPostArraryTest(
            @RequestBody List<LoginDTO> dtos
    ) {
        dtos.stream().forEach(x -> System.out.println(x));
        Map<String, String> userData = new HashMap<>();
        userData.put("id", dtos.get(1).getUserid());
        userData.put("pw", dtos.get(1).getPassword());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userData);
    }

    @DeleteMapping("apiTest")
    @ResponseBody
    public String apiHttpMethodTest() {
        String message = "Delete Mapping";
        return message;
    }

    @PatchMapping("apiTest")
    @ResponseBody
    public String apiPatchTest() {
        String message = "Patch Mapping";
        return message;
    }
    @PutMapping("apiTest")
    @ResponseBody
    public String apiPutTest() {
        String message = "put Mapping";
        return message;
    }
}
