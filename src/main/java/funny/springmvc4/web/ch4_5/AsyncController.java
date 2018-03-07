package funny.springmvc4.web.ch4_5;

import funny.springmvc4.service.ch4_5.PushService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 18:30 2018/3/6
 */

/**
 * 异步任务的实现时通过控制器从另外一个线程返回一个DeferredResult,这里DeferredResult是从PushService中获得的
 */
@Controller
public class AsyncController {
    @Autowired
    private PushService1 pushService;    //  1   定时任务，定时更新DeferredResult
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall(){   //  2   返回给客户端DeferredResult
        return pushService.getAsyncUpdate();
    }

}
