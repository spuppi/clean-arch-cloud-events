package com.spuppi.cleanarchevents.cleanarchcloudevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CleanArchCloudEventsApplication {

	public static void main(String[] args) {

		SpringApplication.run(CleanArchCloudEventsApplication.class, args);

	}
}
