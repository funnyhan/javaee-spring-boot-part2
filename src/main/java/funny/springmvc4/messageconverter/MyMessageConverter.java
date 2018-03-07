package funny.springmvc4.messageconverter;

import funny.springmvc4.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 15:07 2018/3/5
 */
//  1 继承AbstractHttpMessageConverter接口来实现自定义的HttpMEssageConverter
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
    public MyMessageConverter(){
        //  2   新建一个我们自定义的媒体类型 application/x-funny
        super(new MediaType("application","x-funny", Charset.forName("UTF-8")));
    }

    /**
     *  表明本HttpMessageConverter只处理DemoObj这个类
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     *  重写readInternal方法，处理请求的数据。代码表明我们处理有"-"隔开的数据，并专程DemoObj的对象
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(),Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    /**
     * 重写writeInternal,处理如何输出数据到response。此例中，我们在原样输出前面加上hello
     * @param obj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+obj.getId()+"-"+obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
