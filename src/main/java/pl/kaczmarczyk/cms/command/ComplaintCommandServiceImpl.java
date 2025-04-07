package pl.kaczmarczyk.cms.command;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.kaczmarczyk.cms.country.CountryDto;
import pl.kaczmarczyk.cms.country.CountryService;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
class ComplaintCommandServiceImpl implements ComplaintCommandService {

	private static final String COUNTRY_CODE_INVALID = "COUNTRY_CODE_INVALID";
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
		validateCountry(countryDto);
		return commandRepository.save(Complaint.of(command, countryDto.countryCode()));
	}

	private void publishComplaint(Complaint complaint) {
		applicationEventPublisher.publishEvent(ComplaintMapper.INSTANCE.complaintToComplaintEvent(complaint));
	}

	private static void validateCountry(CountryDto countryDto) {
		if (Objects.isNull(countryDto.countryCode())) {
			log.error("The country code is invalid");
			throw new IllegalArgumentException(COUNTRY_CODE_INVALID);
		}
	}
}