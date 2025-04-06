package pl.kaczmarczyk.cms.command;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ComplaintCommandRepository extends JpaRepository<Complaint, Long> {

	Optional<Complaint> findByProductIdAndClaimant(Long productId, String claimant);

}