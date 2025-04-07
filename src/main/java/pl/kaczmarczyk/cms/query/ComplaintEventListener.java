package pl.kaczmarczyk.cms.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.kaczmarczyk.cms.command.ComplaintEvent;

@Slf4j
@Component
@RequiredArgsConstructor
class ComplaintEventListener {

	private final ComplaintQueryRepository repository;

	@EventListener
	public void handleComplaintEvent(ComplaintEvent event) {
		log.info("Handling complaint event {}", event.id());
		repository.add(ComplaintMapper.INSTANCE.complaintEventToComplaint(event));
	}
}