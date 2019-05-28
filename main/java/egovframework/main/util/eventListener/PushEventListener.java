package egovframework.main.util.eventListener;

import org.springframework.context.ApplicationListener;

public class PushEventListener implements ApplicationListener<PushEvent>  {

	@Override
	public void onApplicationEvent(PushEvent event) {
		// TODO Auto-generated method stub
		System.out.println("push!");
	}
}