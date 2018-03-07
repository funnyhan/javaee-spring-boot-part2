package funny.springmvc4.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 18:17 2018/3/6
 */
@Controller
public class SseController {
    @RequestMapping(value = "/push",produces = "text/event-stream;charset=UTF-8")
    public @ResponseBody String push(){
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Test 1,2,3 "+r.nextInt()+"\n\n";
    }
}
