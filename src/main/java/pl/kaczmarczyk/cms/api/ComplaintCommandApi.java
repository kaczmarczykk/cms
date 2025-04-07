package pl.kaczmarczyk.cms.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.kaczmarczyk.cms.command.ComplaintCreateCommand;
import pl.kaczmarczyk.cms.command.ComplaintResponse;
import pl.kaczmarczyk.cms.command.ComplaintUpdateCommand;

@Tag(name = "ComplaintCommand", description = "API for Complaints Command")
public interface ComplaintCommandApi {

	@PostMapping
	@Operation(summary = "Create complaint")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully created"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody ComplaintCreateCommand command,
													  HttpServletRequest httpServletRequest);

	@PutMapping
	@Operation(summary = "Update complaint")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "404", description = "Complaint not found"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	ResponseEntity<ComplaintResponse> updateComplaint(@RequestBody ComplaintUpdateCommand command);
}
