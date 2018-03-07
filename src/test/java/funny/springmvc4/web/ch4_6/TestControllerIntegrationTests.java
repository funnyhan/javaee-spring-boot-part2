package funny.springmvc4.web.ch4_6;

import funny.springmvc4.MyMvcConfig;
import funny.springmvc4.service.ch4_6.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 10:15 2018/3/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
//    1 @WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。它的属性指定是Web资源的位置，
//      默认为src/main/webapp，本例修改为src/main/resources。
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {
    private MockMvc mockMvc;//  2   MockMvc-模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化

    @Autowired
    private DemoService demoService;    //  3   可以在测试用例中注入Spring的Bean
    @Autowired
    WebApplicationContext wac;  //  4   可以注入WebApplicationContext
    @Autowired
    MockHttpSession session;    //  5   可以注入模拟的http session ，此处仅作演示，没有使用
    @Autowired
    MockHttpServletRequest request;  //  6  可以注入模拟的http request，此处仅作演示，没有使用

    @Before //7 @Before在测试开始前进行初始化工作
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();    //  2
    }

    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(get("/normal"))//    8   模拟向/normal进行get请求
                .andExpect(status().isOk())//   9   预期控制返回状态为200
                .andExpect(view().name("page"))//  10   预期view的名称为page
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//   11  预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp
                .andExpect(model().attribute("msg",demoService.saySomething()));    //  12  预期model里的值是demoService.saySomething()返回值hello
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest"))   //  13  模拟向/testRest进行get请求
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))   //14    预期返回值的媒体类型为
                .andExpect(content().string(demoService.saySomething()));// 15  预期返回值得内容为demoService.saySomething()返回值hello
    }
}
