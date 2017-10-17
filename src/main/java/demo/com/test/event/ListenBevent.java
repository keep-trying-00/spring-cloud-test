package demo.com.test.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component //这个一定要加上
public class ListenBevent implements ApplicationListener<AEvent>{

	//@Async
	@Override
	public void onApplicationEvent(AEvent event) {
		System.out.println("BBBBBBBBBBBBBBBBBBB");
	}

}
