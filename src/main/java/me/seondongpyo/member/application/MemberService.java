package me.seondongpyo.member.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.member.domain.Member;
import me.seondongpyo.member.domain.MemberRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public Member create(Member member) {
		return memberRepository.save(member);
	}

	@Transactional(readOnly = true)
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Member findById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
	}

	public void update(Long memberId, Member updateParam) {
		Member member = findById(memberId);
		member.update(updateParam);
	}

	public void delete(Long memberId) {
		memberRepository.deleteById(memberId);
	}
}
