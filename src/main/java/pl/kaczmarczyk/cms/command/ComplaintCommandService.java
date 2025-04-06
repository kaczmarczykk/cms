package pl.kaczmarczyk.cms.command;

import jakarta.validation.Valid;

public interface ComplaintCommandService {

	ComplaintResponse createComplaint(@Valid ComplaintCreateCommand command, String remoteAddress);

	ComplaintResponse updateComplaint(@Valid ComplaintUpdateCommand command);
}
