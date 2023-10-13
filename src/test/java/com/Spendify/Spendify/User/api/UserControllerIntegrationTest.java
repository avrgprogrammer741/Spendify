package com.Spendify.Spendify.User.api;

import com.Spendify.Spendify.User.UserAddRequestDTO;
import com.Spendify.Spendify.User.UserDTO;
import com.Spendify.Spendify.User.UserUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void deleteUser() throws Exception {
        UserDTO userDTO = createUser();
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/user/{id}", userDTO.id()))
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/user/{id}", userDTO.id()))
                .andExpect(status().isNotFound());
    }

    private UserDTO createUser() throws Exception {
        UserAddRequestDTO userRequestDTO = UserAddRequestDTO.builder()
                .name("Maciek")
                .surname("Kowal")
                .password("xqyz")
                .email("kowaldobrykolega@pw.pl")
                .image("ioioio.png")
                .isActive(true)
                .build();

        String userRequestJson = objectMapper.writeValueAsString(userRequestDTO);

        String response = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/user/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequestJson)
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse().getContentAsString();

        return objectMapper.readValue(response, UserDTO.class);
    }

    @Test
    public void updateUser() throws Exception {
        UserDTO userDTO = createUser();
        Long existingUserId = userDTO.id();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/user/{id}", existingUserId))
                .andExpect(status().isOk());

        UserUpdateRequest updatedUserData = UserUpdateRequest.builder()
                .surname("Sep")
                .image("avatar.png")
                .password("xyzqaz")
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/user/{id}", existingUserId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedUserData))
                )
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/user/{id}", existingUserId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", Matchers.equalTo("Sep")))
                .andExpect(jsonPath("$.image", Matchers.equalTo("avatar.png")));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/user/{id}", existingUserId))
                .andExpect(status().is2xxSuccessful());
    }
}
