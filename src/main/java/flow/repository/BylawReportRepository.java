package flow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import flow.domain.RBylawReport;
import flow.dto.ViolationType;

public interface BylawReportRepository extends JpaRepository<RBylawReport, Long> {
	
	@Query("SELECT br FROM RBylawReport br WHERE br.reportType = ?1")
	public Page<RBylawReport> findReportsByViolationType(ViolationType type, Pageable pageable);

}
