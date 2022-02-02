package com.meydan.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MeydanDispather extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class[] classes = {MeydanConfig.class};
		return classes;
	}

	@Override
	protected String[] getServletMappings() {
		String[] maps = {"/"};
		return maps;
	}

}
