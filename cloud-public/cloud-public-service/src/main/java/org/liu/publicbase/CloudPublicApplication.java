package org.liu.publicbase;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class CloudPublicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudPublicApplication.class, args);
	}

}
