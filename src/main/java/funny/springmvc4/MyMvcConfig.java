package funny.springmvc4;

import funny.springmvc4.interceptor.DemoInterceptor;
import funny.springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;
//WebMvcConfigurerAdapter   WebMvcConfigurationSupport
/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 16:54 2018/3/3
 */
@Configuration
@EnableWebMvc   //  1   @EnableWebMvc 注解会开启一些默认配置，如一些ViewResolver或者MessageConverter等
@EnableScheduling
@ComponentScan("funny.springmvc4")
public class MyMvcConfig extends WebMvcConfigurationSupport{    //2 继承WebMvcConfigurationSupport,重写其方法可对springmvc进行配置
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver =new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>
     *这个比较奇怪,理论上应该是不需要的
     * @return
     */
    @Bean
    public HandlerMapping resourceHandlerMapping() {
        return super.resourceHandlerMapping();
    }


    @Override
    protected  void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  3   addResourceLocations指的是文件放置的怒路，addResourceHandler是对外暴露的访问路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    @Bean   //  配置拦截器的Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    /**
     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>
     * 这个比较奇怪,理论上应该是不需要的
     * @return
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        return super.requestMappingHandlerMapping();
    }

    @Override
    protected  void addInterceptors(InterceptorRegistry registry) {  //  2   重写addInterceptor方法，注册拦截器
        super.addInterceptors(registry);
        registry.addInterceptor(demoInterceptor()).addPathPatterns("/**");
    }


    /**
     * 描述 : <HandlerMapping需要显示声明，否则不能快捷定义ViewController>
     * 这个比较奇怪,理论上应该是不需要的
     * @return
     */
    @Bean
    public HandlerMapping viewControllerHandlerMapping(){
        return super.viewControllerHandlerMapping();
    }

    /**
     * 快捷的ViewController，在HelloController中无任何业务处理的简单页面转向写了至少三行代码；在实际开发中会涉及大量这样的页面转向，
     * 若都这样写会很麻烦，我们可以通过在配置中重写addViewControllers来简化配置。
     * 这样实现的代码更简洁，管理更集中
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index2").setViewName("/index2");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");

    }


    /**
     * 通过重写configurePathMatch方法对路径匹配参数配置
     * 譬如此处配置将不再忽略路径参数中带“.”的后面的值
     * @param configurer
     */
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * configureMessageConverter:重载会覆盖掉SpringMVC默认注册的多个HttpMessageConverter
     * extendMessageConverters:仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public List<HttpMessageConverter<?>> httpMessageConvertersList(){
        return super.getMessageConverters();
    }
    @Bean
    public MessageCodesResolver messageCodesResolver(){
        return super.getMessageCodesResolver();
    }
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}
