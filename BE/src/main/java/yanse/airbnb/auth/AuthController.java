//package yanse.airbnb.auth;
//
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.repository.query.Param;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import yanse.airbnb.auth.dto.AccessToken;
//import yanse.airbnb.auth.github.GithubUser;
//import yanse.airbnb.auth.jwt.JwtUtil;
//import yanse.airbnb.domain.member.Members;
//import yanse.airbnb.web.dto.ResponseDto;
//
//@RequiredArgsConstructor
//@RestController
//public class AuthController {
//
//	private final JwtUtil jwtUtil;
//	private final AuthService authService;
//	private final LoginService loginService;
//
//	@GetMapping("/login/oauth")
//	public ResponseDto<Void> login(@Param(value = "code") String code,
//		HttpServletResponse response) {
//		AccessToken accessToken = authService.requestAccessToken(code);
//		Members members = authService.requestUserInfo(accessToken);
//		String jwtToken = jwtUtil.createJwtToken(loginService.findLoginUser(members));
//
//		response.setHeader("ACCESS_TOKEN", jwtToken);
//
//		return new ResponseDto<>(HttpStatus.OK, null);
//	}
//}
