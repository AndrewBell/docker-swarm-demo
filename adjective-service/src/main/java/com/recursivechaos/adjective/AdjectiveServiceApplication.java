package com.recursivechaos.adjective;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class AdjectiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdjectiveServiceApplication.class, args);
    }

    @Value("${properties.location:NOT_LOADED}")
    private String location;

    @RequestMapping(path = "/random")
    public ResponseEntity<AdjectiveResponse> getRandomAdjective() {
        String adjective = getRandomAdjectiveString();
        return new ResponseEntity<>(new AdjectiveResponse(adjective, location), HttpStatus.OK);
    }

    private String getRandomAdjectiveString() {
        List<String> adjectives = Arrays.asList("tasty", "cuddly", "tasteless", "naive", "odd", "scandalous", "highfalutin", "crowdsourced", "distruptive", "nauseating");
        Random rand = new Random();
        return rand.ints(1, 0, adjectives.size()).mapToObj(adjectives::get).findFirst().get();
    }

    private class AdjectiveResponse {

        private String adjective;

        private String hostname;

        private String location;

        AdjectiveResponse(String adjective, String location) {
            this.adjective = adjective;
            this.location = location;
        }

        public String getAdjective() {
            return adjective;
        }

        public String getLocation() {
            return location;
        }

        public String getHostname() {
            try {
                return InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                return "UNKOWN-HOST";
            }
        }

    }
}
