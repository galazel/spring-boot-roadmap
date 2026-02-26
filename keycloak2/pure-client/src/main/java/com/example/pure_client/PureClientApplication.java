package com.example.pure_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PureClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PureClientApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
	}
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(OAuth2AuthorizedClientService authorizedClientService, ClientRegistrationRepository clientRegistrationRepository) {
		var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);

		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials().build();
		manager.setAuthorizedClientProvider(authorizedClientProvider);
		return manager;
	}

	@Bean
	public CommandLineRunner runner(OAuth2AuthorizedClientManager authorizedClientManager, RestTemplate restTemplate, @Value("${service2.url}") String serviceUrl) {
		return args -> {
			var authRequest = OAuth2AuthorizeRequest.withClientRegistrationId("oauth2-client")
					.principal("machine")
					.build();
			var client = authorizedClientManager.authorize(authRequest);
			String token = client.getAccessToken().getTokenValue();

			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			var res = restTemplate.exchange(serviceUrl+"/data", HttpMethod.GET, new HttpEntity<>(headers), String.class);

			System.out.println("Response from service 2: "+res);
		};
	}
}
