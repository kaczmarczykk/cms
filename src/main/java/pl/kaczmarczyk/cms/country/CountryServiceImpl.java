package pl.kaczmarczyk.cms.country;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CountryServiceImpl implements CountryService {

	private static final String CLIENT_ERROR = "CLIENT_ERROR";
	private static final String SERVER_ERROR = "SERVER_ERROR";

	private final WebClient webClient;
	private final CountryProperties countryProperties;

	@Override
	@Cacheable(value = "ipToCountry", key = "#ip", unless = "#result.countryCode == null")
	public CountryDto getCountryByIp(String ip) {
		return webClient.get()
				.uri(countryProperties.getUrl() + ip)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException(CLIENT_ERROR)))
				.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException(SERVER_ERROR)))
				.bodyToMono(CountryDto.class)
				.block();
	}
}