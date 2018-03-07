package funny.springmvc4.web.ch4_3;

import funny.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 9:52 2018/3/5
 */
@Controller //  1   @Controller注解声明此类是一个控制器
@RequestMapping("/anno")    //  2   @RequestMapping("/anno")映射此类访问路径是/anno
public class DemoAnnoController {
    //  3   此方法未标注路径，因此使用类级别的/anno；produces可定制返回的response的媒体类型和字符集，
    //  或需返回值是json对象,则设置produces="application/json;charset=UTF-8"，在后面的章节我们会演示此特性
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    //  4   演示可接收HttpServletRequest作为参数，当然也可以接收HttpServletResponse作为参数。此处的@ResponseBody用在返回值前面。
    public @ResponseBody String index(HttpServletRequest request){
        return "url:" +request.getRequestURL()+" can access";
    }
    //  5   演示接受路径参数，并在方法参数前结合@PathVariable使用，访问路径为/anno/pathvar/XX
    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
        return "url:" +request.getRequestURL()+" can access,str:"+str;
    }
    //  6   演示常规的request参数获取，访问路径/anno/requestParam?id=1
    @RequestMapping(value = "/requestParam",produces ="text/plain;charset=UTF-8" )
    public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
        return "url:" +request.getRequestURL()+" can access,id:"+id;
    }
    //  7   演示解释参数到对象，访问路径为/anno/obj?id=1&name=xx
    @RequestMapping(value = "/obj",produces ="text/plain;charset=UTF-8")
    @ResponseBody// 8   @ResponseBody也可以用在方法上
    public String passObj(DemoObj obj,HttpServletRequest request){
        return "url:" +request.getRequestURL()+" can access,obj id:"+obj.getId()+" obj name:"+obj.getName();
    }
    //  9   演示映射不同的路径到相同的方法，访问路径/anno/name1 或/anno/name2
    @RequestMapping(value = {"/name1","/name2"},produces ="text/plain;charset=UTF-8")//9
    public @ResponseBody String remove(HttpServletRequest request){
        return "url:" +request.getRequestURL()+" can access";
    }
}

