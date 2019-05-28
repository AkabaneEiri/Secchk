package egovframework.main.util.eventListener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class PushService implements ApplicationEventPublisherAware{
	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		// TODO Auto-generated method stub
		this.publisher = publisher;
	}
	
	public void push() {
		PushEvent event = new PushEvent(this);
		publisher.publishEvent(event);
	}
}