package funny.springmvc4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Web配置
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 16:59 2018/3/3
 */
//  1   WebApplicationInitializer是spring提供用来配置Servlet-3.0+配置的接口，
// 从而实现了替代web.xml的位置。实现此接口将会自动被SpringServletContainerInitializer(用来启动Servlet3.0容器)获取到
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context =new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);//  2   新建WebApplicationContext,注册配置类，并将其和当前servletContext关联
        //  3   注册SpringMVC的DispatchServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher"
        ,new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        //开启异步方法支持
        servlet.setAsyncSupported(true);
    }
}
