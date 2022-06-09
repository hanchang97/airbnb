package yanse.airbnb.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yanse.airbnb.domain.member.Members;

@NoArgsConstructor
@Service
public class JwtUtil {
	@Value("${jwt.secret-key}")
	private String SECRET_KEY;

	@Value("jwt.issuer")
	private String ISSUER;

	public String createJwtToken(Members member) {
		return JWT.create()
			.withExpiresAt(new Date())
			.withClaim("githubName", member.getMembersName())
			.withClaim("githubEmail", member.getEmail())
			.withClaim("githubId", member.getGithubId())
			.withClaim("githubAvatarUrl", member.getMembersImage())
			.withIssuer(ISSUER)
			.sign(Algorithm.HMAC256(SECRET_KEY));
	}

}
