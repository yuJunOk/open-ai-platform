package com.example.sdkclient;

import com.example.sdkclient.client.SdkClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengYuJun
 */
@Configuration
@ConfigurationProperties("sdk.client")
@Data
@ComponentScan
public class SdkClientConfig {

	private String accessKey;

	private String secretKey;

	@Bean
	public SdkClient sdkClient() {
		return new SdkClient(accessKey, secretKey);
	}

}