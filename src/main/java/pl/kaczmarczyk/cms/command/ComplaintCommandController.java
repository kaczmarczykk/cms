package pl.kaczmarczyk.cms.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kaczmarczyk.cms.api.ComplaintCommandApi;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
class ComplaintCommandController implements ComplaintCommandApi {

	private final ComplaintCommandService commandService;

	@Override
	public ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody ComplaintCreateCommand command,
															 HttpServletRequest httpServletRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.commandService.createComplaint(command, httpServletRequest.getRemoteAddr()));
	}

	@Override
	public ResponseEntity<ComplaintResponse> updateComplaint(@RequestBody ComplaintUpdateCommand command) {
		return ResponseEntity.ok(this.commandService.updateComplaint(command));
	}
}