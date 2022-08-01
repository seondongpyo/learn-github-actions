package me.seondongpyo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LearnGithubActionsApplicationTests {

	@Value("${github.actions.variable}")
	private String variable;

	@Test
	void contextLoads() {
		assertThat(variable).isEqualTo("Github Actions");
	}

}
