package cn.wk.association.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//网上看到：继承WebMvcConfigrationSupport会让Spring-boot对mvc的自动配置失效的，应该实现WebMvcConfigurer接口
@Configuration
@EnableWebMvc//启用Spring MVC
@ComponentScan("cn.wk.association.controller")
public class WebConfig  extends WebMvcConfigurationSupport {
	// 配置JSP视图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
//		resolver.setOrder(0);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
//    <bean id="multipartResolver"
//            class="org.springframework.web.multipart.commons.CommonsMultipartResolver">    
//            <!-- 默认编码 -->  
//            <property name="defaultEncoding" value="utf-8" />    
//            <!-- 文件大小最大值 -->  
//            <property name="maxUploadSize" value="10485760000" />    
//            <!-- 内存中的最大值 -->  
//            <property name="maxInMemorySize" value="40960"/>    
//        </bean> 
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
//		multipartResolver.setDefaultEncoding("utf-8");
//		multipartResolver.setMaxUploadSize(1048000000);
//		multipartResolver.setMaxInMemorySize(40960);
//		return multipartResolver;
//	}
	// 配置静态资源的处理
	@Override 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}
