package me.seondongpyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@PropertySource("classpath:/application.properties")
class LearnGithubActionsApplicationTests {

	@Value("${test.variable}")
	private String variable;

	@Test
	void contextLoads() {
		assertThat(variable).isEqualTo("secrets test");
	}

}
