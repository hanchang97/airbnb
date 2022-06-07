package yanse.airbnb.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yanse.airbnb.auth.dto.AccessToken;
import yanse.airbnb.auth.github.GithubUser;
import yanse.airbnb.service.AuthService;
import yanse.airbnb.service.LoginService;
import yanse.airbnb.web.dto.ResponseDto;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;
	private final LoginService loginService;

	@GetMapping("/auth/login")
	public ResponseDto<Void> login(@Param(value = "code") String code) {
		AccessToken accessToken = authService.requestAccessToken(code);
		GithubUser githubUser = authService.requestUserInfo(accessToken);
		loginService.findLoginUser(githubUser, accessToken);
		return new ResponseDto<>(HttpStatus.OK, null);
	}
}
