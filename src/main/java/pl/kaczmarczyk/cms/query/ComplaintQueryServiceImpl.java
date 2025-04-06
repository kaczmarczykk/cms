package pl.kaczmarczyk.cms.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ComplaintQueryServiceImpl implements ComplaintQueryService {

	private final ComplaintQueryRepository queryRepository;

	@Override
	public ComplaintListResponse getComplaints() {
		return new ComplaintListResponse(ComplaintMapper.INSTANCE.map(queryRepository.all()));
	}
}
