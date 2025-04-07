package pl.kaczmarczyk.cms.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kaczmarczyk.cms.query.ComplaintListResponse;

@Tag(name = "ComplaintQuery", description = "API for Complaints Query")
public interface ComplaintQueryApi {

	@GetMapping
	@Operation(summary = "Get complaints")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched all complaints"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	ResponseEntity<ComplaintListResponse> getComplaints();
}
