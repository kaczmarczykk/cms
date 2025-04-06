package pl.kaczmarczyk.cms.command;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "complaint", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "claimant"})})
class Complaint implements Serializable {

	@Serial
	private static final long serialVersionUID = -8049419272522430668L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaintSeqGen")
	@SequenceGenerator(name = "complaintSeqGen", sequenceName = "complaint_id_seq", allocationSize = 1)
	private Long id;

	@NotNull
	private Long productId;

	@NotBlank
	private String claimant;

	@NotBlank
	private String content;

	private LocalDateTime createdAt;

	@NotNull
	private String country;

	private int counter;

	public static Complaint of(ComplaintCreateCommand command, String country) {
		return new Complaint(null, command.productId(), command.claimant(), command.content(), LocalDateTime.now(), country, 0);
	}

	public void incrementCounter() {
		this.counter++;
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
