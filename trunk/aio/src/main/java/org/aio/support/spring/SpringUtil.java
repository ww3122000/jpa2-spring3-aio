package org.aio.support.spring;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@Component
public class SpringUtil implements ApplicationContextAware, ServletContextAware {

	private static ApplicationContext applicationContext;
	private static ServletContext servletContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public void setServletContext(ServletContext servletContext) {
		SpringUtil.servletContext = servletContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @param name
	 *            Bean注册名称
	 * 
	 * @return bean实例
	 * 
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 通过class类型，获取bean
	 * @param T
	 * @return
	 * @throws BeansException
	 */
	public static Object getBean(Class<?> T) throws BeansException {
		return applicationContext.getBean(T);
	}

	/**
	 * @功能描述：获取所有通过Annotation映射的URL，用于权限分配
	 * 
	 * @return
	 */
	public static Map<String, Object> getHandlerMap() {
		DefaultAnnotationHandlerMapping defaultAnnotationHandlerMapping = applicationContext
				.getBean(DefaultAnnotationHandlerMapping.class);
		return defaultAnnotationHandlerMapping.getHandlerMap();
	}
}