package yanse.airbnb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yanse.airbnb.auth.dto.AccessToken;
import yanse.airbnb.auth.github.GithubUser;
import yanse.airbnb.domain.member.Members;
import yanse.airbnb.domain.member.repository.MembersRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final MembersRepository membersRepository;

	//TODO:예외처리
	public Members findLoginUser(GithubUser githubUser, AccessToken accessToken) {
		if (!isDuplicate(githubUser)) {
			Members members = githubUser.toEntity(accessToken);
			membersRepository.save(members);
			return members;
		}
		return membersRepository.findByGithubId(githubUser.getGithubId())
			.orElseThrow(RuntimeException::new);
	}

	private boolean isDuplicate(GithubUser githubUser) {
		return membersRepository.findByGithubId(githubUser.getGithubId()).isPresent();
	}
}
