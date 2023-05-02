package spring;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class MemberRegisterService { //신입회원 등록해주는 service
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());//등록일이 자동으로 만들어진다
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
