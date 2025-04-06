package pl.kaczmarczyk.cms.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
class ComplaintCommandController {

	private final ComplaintCommandService commandService;

	@PostMapping
	public ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody ComplaintCreateCommand command,
															 HttpServletRequest httpServletRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.commandService.createComplaint(command, httpServletRequest.getRemoteAddr()));
	}

	@PutMapping
	public ResponseEntity<ComplaintResponse> updateComplaint(@RequestBody ComplaintUpdateCommand command) {
		return ResponseEntity.ok(this.commandService.updateComplaint(command));
	}
}