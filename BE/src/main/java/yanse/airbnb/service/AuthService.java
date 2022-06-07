package yanse.airbnb.service;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import yanse.airbnb.auth.dto.AccessToken;
import yanse.airbnb.auth.dto.AccessTokenRequest;
import yanse.airbnb.auth.github.GithubUser;


@Service
public class AuthService {

	@Value("${github.client.id}")
	private String CLIENT_ID;

	@Value("${github.client.secrets}")
	private String CLIENT_SECRETS;

	@Value("${github.redirect.uri}")
	private String REDIRECT_URI;

	@Value("${github.access.token.uri}")
	private String ACCESS_TOKEN_URI;

	@Value("${github.user.uri}")
	private String USER_URI;

	private final HttpClient httpClient = HttpClient.create()
		.resolver(DefaultAddressResolverGroup.INSTANCE);
	private final WebClient webClient = WebClient.builder()
		.clientConnector(new ReactorClientHttpConnector(httpClient)).build();

	//TODO: 예외처리 커스텀
	public AccessToken requestAccessToken(String code) {
		AccessTokenRequest accessTokenRequest = AccessTokenRequest.builder()
			.clientId(CLIENT_ID)
			.clientSecret(CLIENT_SECRETS)
			.code(code)
			.redirectUri(REDIRECT_URI)
			.build();
		return webClient.post()
			.uri(ACCESS_TOKEN_URI)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(accessTokenRequest)
			.retrieve()
			.bodyToMono(AccessToken.class)
			.blockOptional()
			.orElseThrow(RuntimeException::new);
	}


	public GithubUser requestUserInfo(AccessToken accessToken) {
		return webClient.get()
			.uri(USER_URI)
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, accessToken.authorizationHeaderValue())
			.retrieve()
			.bodyToMono(GithubUser.class)
			.blockOptional()
			.orElseThrow(RuntimeException::new);
	}
}
