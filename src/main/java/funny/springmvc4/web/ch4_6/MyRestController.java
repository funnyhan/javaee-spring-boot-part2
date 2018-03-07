package funny.springmvc4.web.ch4_6;

import funny.springmvc4.service.ch4_6.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 10:53 2018/3/7
 */
@Controller
public class MyRestController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String testRest(){
        return demoService.saySomething();
    }
}
