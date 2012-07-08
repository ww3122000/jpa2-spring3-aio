package org.aio.support.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 
 */
public class RichFreeMarkerView extends FreeMarkerView {
	/**
	 * ${base}
	 */
	public static final String CONTEXT_PATH = "base";

	/**
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
		
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, request.getContextPath());
	}
}