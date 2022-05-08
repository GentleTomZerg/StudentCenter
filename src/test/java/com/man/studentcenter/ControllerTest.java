package com.man.studentcenter;

import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.service.state.Registered;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testLogin() throws Exception {
        String url = "/";
        // Test TokenLogin
        mvc.perform(MockMvcRequestBuilders.post(url)
                .param("token", "123")
                .param("loginStrategy", "0")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Autowired
    Registered registered;
    @Test
    public void testSubscribe() throws Exception {
        String url = "/subscribe";
        /*
        *  模仿请求
        * */
        Student student = new Student();
        student.setToken(222);
        student.setStatus(1);
        student.setState(registered);
        student.setNewsletters(new ArrayList<>());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("student", student);

        String json = "My Manchester News, Sport News, Stellify, The Careers News";

        mvc.perform(MockMvcRequestBuilders.post(url)
                .session(session)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
