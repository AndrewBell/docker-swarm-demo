package com.recursivechaos.noun;

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
public class NounServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NounServiceApplication.class, args);
	}

    @RequestMapping(path = "/random")
    public ResponseEntity<NounResponse> getRandomNoun() {
        String noun = getRandomNounString();
        return new ResponseEntity<>(new NounResponse(noun), HttpStatus.OK);
    }

	private String getRandomNounString() {
		List<String> nouns = Arrays.asList("cloud", "microservice", "app", "microtransaction", "social media network", "sharing economy", "blockchain", "darknet", "SaaS", "serverless");
		Random rand = new Random();
		return rand.ints(1, 0, nouns.size()).mapToObj(nouns::get).findFirst().get();
	}

	private class NounResponse {

		private String noun;

		private String hostname;

		NounResponse(String noun) {
			this.noun = noun;
		}

		public String getNoun() {
			return noun;
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
