package funny.springmvc4.web.ch4_3;

import funny.springmvc4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 10:08 2018/3/5
 */
@RestController //  1   使用@RestController，声明是控制器，并且返回数据时不需要@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {
    @RequestMapping(value = "/getjson",produces ={"application/json;charset=UTF-8"})//  2 返回数据的媒体类型为json
    public DemoObj getJson(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");// 3   直接返回对象，对象会自动转换成json
    }
    @RequestMapping(value = "/getxml",produces = {"application/xml;charset=UTF-8"})//   4  返回数据的媒体类型为xml
    public DemoObj getxml(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");// 5 直接返回对象，对象会自动转换成xml
    }
}
