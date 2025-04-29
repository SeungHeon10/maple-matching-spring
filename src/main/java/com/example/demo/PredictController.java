package com.example.demo;

import com.example.demo.MultipartInputStreamFileResource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PredictController {

    @GetMapping("/")
    public String index() {
        return "quiz";
    }

    @PostMapping("/recognize")
    public ResponseEntity<String> recognizeNpc(@RequestParam("file") MultipartFile file) throws IOException {
        // Python FastAPI ¼­¹ö·Î Àü¼Û
        String url = "https://SeungHeon10-maple-image-matcher.hf.space/match";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
