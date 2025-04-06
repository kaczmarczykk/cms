package pl.kaczmarczyk.cms.command;

import java.time.LocalDateTime;

public record ComplaintEvent(Long id,
							 Long productId,
							 String claimant,
							 String content,
							 LocalDateTime createdAt,
							 String country,
							 int counter) {
}