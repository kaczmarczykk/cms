package pl.kaczmarczyk.cms.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ComplaintCreateCommand(@NotNull(message = "NOT_NULL") Long productId,
									 @NotBlank(message = "NOT_BLANK") String claimant,
									 @NotBlank(message = "NOT_BLANK") @Size(max = 1000, message = "MAX_SIZE") String content) {
}