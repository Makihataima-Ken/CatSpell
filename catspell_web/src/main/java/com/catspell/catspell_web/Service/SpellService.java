package com.catspell.catspell_web.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class SpellService {

    public String translatePhrase(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.funtranslations.com/translate/pirate.json";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> request = new HttpEntity<>("text=" + text, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            JSONObject json = new JSONObject(response.getBody());
            return json.getJSONObject("contents").getString("translated");

        } catch (Exception e) {
            e.printStackTrace();
            return "[Spell failed — dark forces interfered]";
        }
    }

    public String getCatFact() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://catfact.ninja/fact";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(response.getBody());
            return json.getString("fact");
        } catch (Exception e) {
            e.printStackTrace();
            return "[No cat fact could be summoned]";
        }
    }
}
