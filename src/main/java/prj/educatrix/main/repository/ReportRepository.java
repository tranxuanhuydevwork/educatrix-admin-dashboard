package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prj.educatrix.main.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
