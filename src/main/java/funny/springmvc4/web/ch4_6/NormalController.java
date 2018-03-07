package funny.springmvc4.web.ch4_6;

import funny.springmvc4.service.ch4_6.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 10:48 2018/3/7
 */
@Controller
public class NormalController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/normal")
    public String testPage(Model model){
        model.addAttribute("msg",demoService.saySomething());
        return "page";
    }
}
