package me.seondongpyo.member.ui;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.member.application.MemberService;
import me.seondongpyo.member.domain.Member;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberRestController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<MemberResponseDTO> create(@RequestBody MemberRequestDTO request) {
		Member member = memberService.create(request.toEntity());
		return ResponseEntity.created(URI.create("/api/members/" + member.getId()))
			.body(new MemberResponseDTO(member));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MemberResponseDTO> findById(@PathVariable Long id) {
		Member member = memberService.findById(id);
		return ResponseEntity.ok(new MemberResponseDTO(member));
	}

	@GetMapping
	public ResponseEntity<List<MemberResponseDTO>> findAll() {
		List<MemberResponseDTO> members = memberService.findAll()
			.stream()
			.map(MemberResponseDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(members);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id,
									   @RequestBody MemberRequestDTO request) {

		memberService.update(id, request.toEntity());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		memberService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
