package pl.kaczmarczyk.cms.common;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CountryServiceImpl implements CountryService {

	private final WebClient webClient;
	private final CountryProperties countryProperties;

	@Override
	@Cacheable(value = "ipToCountry", key = "#ip")
	public CountryDto getCountryByIp(String ip) {
		return new CountryDto("Poland", "PL");
//		return webClient.get()
//				.uri(countryProperties.getUrl() + ip)
//				.retrieve()
//				.onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Cooo")))
//				.onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Cooo1")))
//				.bodyToMono(CountryDto.class)
//				.block();
	}
}