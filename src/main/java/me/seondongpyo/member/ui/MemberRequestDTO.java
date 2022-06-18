package me.seondongpyo.member.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seondongpyo.member.domain.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDTO {

	private String name;
	private int age;

	public Member toEntity() {
		return new Member(name, age);
	}
}
