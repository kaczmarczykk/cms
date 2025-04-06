package pl.kaczmarczyk.cms.query;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
class ComplaintQueryRepository {

	private final Map<Long, Complaint> COMPLAINTS = new ConcurrentHashMap<>();

	public void add(Complaint complaint) {
		COMPLAINTS.put(complaint.id(), complaint);
	}

	public List<Complaint> all() {
		return new ArrayList<>(COMPLAINTS.values());
	}
}
