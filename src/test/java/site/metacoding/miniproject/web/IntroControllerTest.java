package site.metacoding.miniproject.web;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.intro.Intro;
import site.metacoding.miniproject.domain.intro.IntroDao;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroSaveReqDto;
import site.metacoding.miniproject.dto.intro.IntroReqDto.IntroUpdateReqDto;
import site.metacoding.miniproject.util.SHA256;

@ActiveProfiles("test") // application-test으로 실행
@Sql("classpath:truncate.sql") // 메서드 실행직전에 drop table
@Transactional // 트랜잭션 안붙이면 영속성 컨텍스트에서 DB로 flush 안됨 (Hibernate 사용시) my마티스는 필요 x
@AutoConfigureMockMvc // MockMvc Ioc 컨테이너에 등록(가짜)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class IntroControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private IntroDao introDao;

    @Autowired
    private SHA256 sha256;

    private MockHttpSession session;

    // 트랜잭션 발동
    // @BeforeEach // 메서드 실행직전마다 실행
    // public void sessionInit() {// 가짜 세션유저 생성
    // session = new MockHttpSession();
    // Company company = Company.builder().companyId(1).companyName("삼성").build();
    // session.setAttribute("sessionUser", new SessionUser(intro));
    // }

    @Test
    public void save_test() throws Exception {
        // given
        IntroSaveReqDto introSaveReqDto = new IntroSaveReqDto();
        introSaveReqDto.setIntroConame("삼성");
        introSaveReqDto.setCompanyId(1);
        introSaveReqDto.setIntroBirth("1111");
        introSaveReqDto.setIntroContent("오지마");
        introSaveReqDto.setIntroId(1);
        introSaveReqDto.setIntroSal("1000만원");
        introSaveReqDto.setIntroWellfare("복지좋아");
        introSaveReqDto.setIntroLocation("광주");
        introSaveReqDto.setIntroTask("잠자기");

        String body = om.writeValueAsString(introSaveReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/co/intro/insert").content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
    }

    // @Test
    // public void findByCompanyId_test() throws Exception {
    // // given
    // Integer companyId = 1;
    // System.out.println("디버그 : " + companyId);
    // // when
    // ResultActions resultActions = mvc
    // .perform(MockMvcRequestBuilders.get("/cs/co/companyIntroDetail/" +
    // companyId).accept(APPLICATION_JSON));
    // System.out.println("디버그 : " + companyId);

    // // then
    // MvcResult mvcResult = resultActions.andReturn();
    // System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
    // resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.Coname").value("삼성"));
    // }

    @Test
    public void findByDetail_test() throws Exception {
        // given
        Long introId = 2L;
        System.out.println("디버그 : " + introId);
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/emp/companyIntroDetail/" +
                        introId).accept(APPLICATION_JSON));
        System.out.println("디버그 : " + introId);

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.Coname").value("삼성"));
    }

    @Test
    public void findAll_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/emp/companyList").accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1));
        // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].title").value("스프링1강"));
    }

    @Test
    public void update_test() throws Exception {
        // given
        Long introId = 1L;
        IntroUpdateReqDto introUpdateReqDto = new IntroUpdateReqDto();
        introUpdateReqDto.setIntroId(1);
        introUpdateReqDto.setCompanyName("바보");
        introUpdateReqDto.setIntroBirth("1111");
        introUpdateReqDto.setIntroContent("오지마");
        introUpdateReqDto.setIntroSal("1000만원");
        introUpdateReqDto.setIntroWellfare("복지좋아");
        introUpdateReqDto.setIntroLocation("광주");
        introUpdateReqDto.setIntroTask("잠자기");
        String body = om.writeValueAsString(introUpdateReqDto);
        System.out.println("디버그 : " + introId);
        System.out.println("디버그 : " + body);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/co/company/update/" + introId).content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1L));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.introContent").value("오지마"));
    }

}
