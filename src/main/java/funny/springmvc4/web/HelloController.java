package funny.springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 简单的控制器
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 17:05 2018/3/3
 */
@Controller //  1   利用@Controller 注解声明是一个控制器
public class HelloController {
    @RequestMapping("/index")   //2 利用@RequestMapping配置URL和方法之间的映射
    public String hello(){
        return "index";     //3 通过之前ViewResolver的Bean配置，返回值为index,说明我们的页面放置的路径是/WEB-INF/classes/views/index.jsp
    }
    @RequestMapping("/error")
    public String error(Model model){
        model.addAttribute("errorMessage","this is a errorMessage");
        return "error";
    }
}
