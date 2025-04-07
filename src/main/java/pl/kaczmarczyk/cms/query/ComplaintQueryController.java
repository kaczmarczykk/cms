package pl.kaczmarczyk.cms.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczmarczyk.cms.api.ComplaintQueryApi;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
class ComplaintQueryController implements ComplaintQueryApi {

	private final ComplaintQueryService queryService;

	@Override
	public ResponseEntity<ComplaintListResponse> getComplaints() {
		return ResponseEntity.ok(this.queryService.getComplaints());
	}
}
