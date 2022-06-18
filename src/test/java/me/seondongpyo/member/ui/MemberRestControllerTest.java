package me.seondongpyo.member.ui;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.seondongpyo.member.application.MemberService;
import me.seondongpyo.member.domain.Member;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberRestControllerTest {

	private static final String BASE_URI = "/api/members";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MemberService memberService;

	@DisplayName("새로운 회원을 등록한다.")
	@Test
	void create() throws Exception {
		MemberRequestDTO member = new MemberRequestDTO("hong", 30);

		mvc.perform(post(BASE_URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(member)))
			.andExpect(status().isCreated());
	}

	@DisplayName("특정 회원을 조회한다.")
	@Test
	void findById() throws Exception {
		Member member = memberService.create(new Member("hong", 30));

		mvc.perform(get(BASE_URI + "/{id}", member.getId()))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(member)));
	}

	@DisplayName("회원 목록을 조회한다.")
	@Test
	void findAll() throws Exception {
		Member kim = memberService.create(new Member("kim", 20));
		Member park = memberService.create(new Member("park", 30));

		List<MemberResponseDTO> response = List.of(new MemberResponseDTO(kim), new MemberResponseDTO(park));

		mvc.perform(get(BASE_URI))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(response)));
	}

	@DisplayName("회원을 수정한다.")
	@Test
	void update() throws Exception {
		Member kim = memberService.create(new Member("kim", 20));
		MemberRequestDTO updateParam = new MemberRequestDTO("hong", 30);

		mvc.perform(put(BASE_URI + "/{id}", kim.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateParam)))
			.andExpect(status().isOk());

		Member member = memberService.findById(kim.getId());
		assertThat(member.getName()).isEqualTo(updateParam.getName());
		assertThat(member.getAge()).isEqualTo(updateParam.getAge());
	}

	@DisplayName("회원을 삭제한다.")
	@Test
	void deleteById() throws Exception {
		Member kim = memberService.create(new Member("kim", 20));
		Long id = kim.getId();

		mvc.perform(delete(BASE_URI + "/{id}", id))
			.andExpect(status().isNoContent());

		assertThatThrownBy(() -> memberService.findById(id))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
