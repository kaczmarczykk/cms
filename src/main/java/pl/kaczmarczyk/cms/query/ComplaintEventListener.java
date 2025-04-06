package pl.kaczmarczyk.cms.query;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.kaczmarczyk.cms.command.ComplaintEvent;

@Component
@RequiredArgsConstructor
class ComplaintEventListener {

	private final ComplaintQueryRepository repository;

	@EventListener
	public void handleComplaintEvent(ComplaintEvent event) {
		repository.add(ComplaintMapper.INSTANCE.complaintEventToComplaint(event));
	}
}