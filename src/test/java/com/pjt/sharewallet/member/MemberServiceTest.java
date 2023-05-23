package com.pjt.sharewallet.member;

import com.google.gson.Gson;
import com.pjt.sharewallet.member.dto.MemberRequest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pjt.sharewallet.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }


    @Test
    void 회원가입() throws Exception {

        MemberRequest memberRequest = MemberRequest.builder()
                .memberId("이름1234")
                .password("pwd1234")
                .build();

        String json = new Gson().toJson(memberRequest);

        mockMvc.perform(
                post("/members") // 요청 전송
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        // json 형식의 데이터 전송 명시
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print()); // 요청과 응답 정보 전체 출력
    }

    @Test
    void 아이디_중복_체크() throws Exception {

        MemberRequest memberRequest = MemberRequest.builder()
                .memberId("이름1234")
                .password("pwd1234")
                .build();

        memberService.join(memberRequest);

        String json = new Gson().toJson(memberRequest);

        mockMvc.perform(
                        post("/members") // 요청 전송
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                // json 형식의 데이터 전송 명시
                                .content(json))
                .andExpect(status().isConflict())
                .andDo(print()); // 요청과 응답 정보 전체 출력
    }

    @Test
    void 로그인() throws Exception {

        MemberRequest memberRequest = MemberRequest.builder()
                .memberId("이름1234")
                .password("pwd1234")
                .build();

        memberService.join(memberRequest);

        String json = new Gson().toJson(memberRequest);

        mockMvc.perform(
                        post("/members/login") // 요청 전송
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                // json 형식의 데이터 전송 명시
                                .content(json))
                .andExpect(status().isOk())
                .andDo(print()); // 요청과 응답 정보 전체 출력
    }

    @Test
    void 예외_미존재_아이디_로그인() throws Exception {

        MemberRequest memberRequest = MemberRequest.builder()
                .memberId("이름1234")
                .password("pwd1234")
                .build();

        String json = new Gson().toJson(memberRequest);

        mockMvc.perform(
                        post("/members/login") // 요청 전송
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                // json 형식의 데이터 전송 명시
                                .content(json))
                .andExpect(status().isUnauthorized())
                .andDo(print()); // 요청과 응답 정보 전체 출력
    }
}
