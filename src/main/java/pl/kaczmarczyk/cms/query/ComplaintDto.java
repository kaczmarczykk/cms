package pl.kaczmarczyk.cms.query;

import java.time.LocalDateTime;

record ComplaintDto(Long id,
					Long productId,
					String claimant,
					String content,
					LocalDateTime createdAt,
					String country,
					int counter) {
}