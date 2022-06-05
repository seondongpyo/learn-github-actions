package me.seondongpyo.team.ui;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamRestController.class)
class TeamRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void create() throws Exception {
        mvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"teamA\"}"))
            .andExpect(status().isCreated());
    }
}
