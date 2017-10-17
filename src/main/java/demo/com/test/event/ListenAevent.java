package demo.com.test.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class ListenAevent implements ApplicationListener<AEvent>{

	//@Async
	@Override
	public void onApplicationEvent(AEvent event) {
		System.out.println("aaaaaaaaaaaaaaa");
	}

}
