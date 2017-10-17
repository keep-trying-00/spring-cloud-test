package demo.com.test.event;

import org.springframework.context.ApplicationEvent;
public class AEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AEvent(Object source) {
		super(source);
	}
	
}
