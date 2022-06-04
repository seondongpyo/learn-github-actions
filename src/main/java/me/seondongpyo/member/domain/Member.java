package me.seondongpyo.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int age;

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void update(Member updateParam) {
		this.name = updateParam.getName();
		this.age = updateParam.getAge();
	}
}
