package pl.kaczmarczyk.cms.command;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.kaczmarczyk.cms.common.CountryDto;
import pl.kaczmarczyk.cms.common.CountryService;

@Service
@RequiredArgsConstructor
class ComplaintCommandServiceImpl implements ComplaintCommandService {

	private final ComplaintCommandRepository commandRepository;
	private final CountryService countryService;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Override
	@Transactional
	public ComplaintResponse createComplaint(ComplaintCreateCommand command, String remoteAddress) {
		Complaint complaint = commandRepository.findByProductIdAndClaimant(command.productId(), command.claimant())
				.map(ComplaintCommandServiceImpl::incrementCounter)
				.orElseGet(() -> createNewComplaint(command, remoteAddress));
		publishComplaint(complaint);
		return new ComplaintResponse(complaint.getId());
	}

	@Override
	@Transactional
	public ComplaintResponse updateComplaint(ComplaintUpdateCommand command) {
		Complaint complaint = commandRepository.findById(command.id())
				.orElseThrow(EntityNotFoundException::new);
		complaint.updateContent(command.content());
		publishComplaint(complaint);
		return new ComplaintResponse(command.id());
	}

	private static Complaint incrementCounter(Complaint existing) {
		existing.incrementCounter();
		return existing;
	}

	private Complaint createNewComplaint(ComplaintCreateCommand command, String remoteAddress) {
		CountryDto countryDto = countryService.getCountryByIp(remoteAddress);
		return commandRepository.save(Complaint.of(command, countryDto.countryCode()));
	}

	private void publishComplaint(Complaint complaint) {
		applicationEventPublisher.publishEvent(ComplaintMapper.INSTANCE.complaintToComplaintEvent(complaint));
	}
}
