package cn.wk.association.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//���Ͽ������̳�WebMvcConfigrationSupport����Spring-boot��mvc���Զ�����ʧЧ�ģ�Ӧ��ʵ��WebMvcConfigurer�ӿ�
@Configuration
@EnableWebMvc//����Spring MVC
@ComponentScan("cn.wk.association.controller")
public class WebConfig  extends WebMvcConfigurationSupport {
	// ����JSP��ͼ������
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
//            <!-- Ĭ�ϱ��� -->  
//            <property name="defaultEncoding" value="utf-8" />    
//            <!-- �ļ���С���ֵ -->  
//            <property name="maxUploadSize" value="10485760000" />    
//            <!-- �ڴ��е����ֵ -->  
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
	// ���þ�̬��Դ�Ĵ���
	@Override 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}
