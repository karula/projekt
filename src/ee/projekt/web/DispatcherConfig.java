package ee.projekt.web;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class DispatcherConfig extends WebMvcConfigurerAdapter {

	public InternalResourceViewResolver jspResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setViewClass(JstlView.class);
		
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	
}
