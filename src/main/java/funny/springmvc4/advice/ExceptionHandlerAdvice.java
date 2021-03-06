package funny.springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 11:11 2018/3/5
 */
@ControllerAdvice   //  1  @ControllerAdvice 声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册为Spring的Bean
public class ExceptionHandlerAdvice {
    //  2 @ExceptionHandler在此处定义全局处理，通过@ExceptionHandler的value属性可过滤拦截的条件
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");  // error页面
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute //  3   此处使用@ModelAttribute注解将键值对添加到全局，所有注解@RequestMapping的方法都可获得此键值对
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");   //  3
    }

    @InitBinder  //  4   通过注解@InitBinder定制WebDataBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");    //  5   此处演示忽略request参数的id
    }
}
