package funny.springmvc4.web.ch4_5;

import funny.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 15:21 2018/3/5
 */
@Controller
public class ConverterController {
    //  1   指定返回的媒体类型为我们自定义的媒体类型application/x-funny
    @RequestMapping(value = "/convert",produces = {"application/x-funny"})
    public @ResponseBody DemoObj convert(DemoObj obj){
        return obj;
    }
}
