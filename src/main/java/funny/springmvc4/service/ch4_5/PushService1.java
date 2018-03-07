package funny.springmvc4.service.ch4_5;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 18:31 2018/3/6
 */

/**
 * 1、在PushService里长生DeferredResult给控制器使用，通过@Scheduled注解的方法定时更新DeferredResult.
 */
@Service
public class PushService1 {
    private DeferredResult<String> deferredResult;  //  1

    public DeferredResult<String> getAsyncUpdate() {    //  1
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh(){  //  1
        if(deferredResult != null){
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }

}
