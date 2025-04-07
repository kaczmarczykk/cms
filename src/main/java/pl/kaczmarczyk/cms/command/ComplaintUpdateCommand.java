package pl.kaczmarczyk.cms.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComplaintUpdateCommand(@NotNull(message = "NOT_NULL") Long id,
									 @NotBlank(message = "NOT_BLANK") String content) {
}