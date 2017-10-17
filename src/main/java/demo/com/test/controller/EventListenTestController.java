package demo.com.test.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.com.test.event.AEvent;

@Controller
public class EventListenTestController implements ApplicationContextAware{
	private ApplicationContext context;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@RequestMapping("/tt")
	public void test(){
		System.out.println("xx");
		this.context.publishEvent(new AEvent("xxx"));
		System.out.println("oo");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
