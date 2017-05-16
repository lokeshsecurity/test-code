package bh.gov.cio.gbs.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware{

	public static String SUPPLEMENTARY_MODEL_MANAGER = "modelManager";
	public static String BOARD_SERVICE_MANAGER = "boardService";
	
	private static ApplicationContext CONTEXT;

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		CONTEXT = ctx;
	}

	public static Object getBean(String name) {
		return CONTEXT.getBean(name);
	}
}
