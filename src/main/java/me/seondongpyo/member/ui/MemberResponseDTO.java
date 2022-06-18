package me.seondongpyo.member.ui;

import lombok.Getter;
import me.seondongpyo.member.domain.Member;

@Getter
public class MemberResponseDTO {

	private final Long id;
	private final String name;
	private final int age;

	public MemberResponseDTO(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.age = member.getAge();
	}
}
