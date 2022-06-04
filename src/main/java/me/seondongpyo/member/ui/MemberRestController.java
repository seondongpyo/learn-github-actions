package me.seondongpyo.member.ui;

import java.net.URI;
import java.util.List;

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
	public ResponseEntity<Member> create(@RequestBody Member request) {
		Member member = memberService.create(request);
		return ResponseEntity.created(URI.create("/api/members/" + member.getId()))
			.body(member);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> findById(@PathVariable Long id) {
		return ResponseEntity.ok(memberService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<Member>> findAll() {
		return ResponseEntity.ok(memberService.findAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id,
										 @RequestBody Member request) {

		memberService.update(id, request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		memberService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
