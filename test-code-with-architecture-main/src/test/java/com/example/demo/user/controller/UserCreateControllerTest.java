package com.example.demo.user.controller;

import com.example.demo.common.service.port.UuidHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class UserCreateControllerTest {

    @Test
    public void 사용자는_회원_가입을_할_수_있고_회원가입된_사용자는_PENDING_상태이다() throws Exception {

        //given
        TestContainer testContainer = TestContainer.builder()
                .uuidHolder(new UuidHolder() {
                    @Override
                    public String random() {
                        return "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";
                    }
                })
                .build();
        UserCreate userCreate = UserCreate.builder()
                .email("kok202@naver.com")
                .nickname("kok202")
                .address("Pangyo")
                .build();

        //when
        ResponseEntity<UserResponse> result = testContainer.userCreateController.createUser(userCreate);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getEmail()).isEqualTo("kok202@naver.com");
        assertThat(result.getBody().getNickname()).isEqualTo("kok202");
        assertThat(result.getBody().getLastLoginAt()).isNull();
        assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(testContainer.userRepository.getById(1).getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
    }

}