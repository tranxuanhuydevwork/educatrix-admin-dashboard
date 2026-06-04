package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prj.educatrix.main.domain.File;

public interface FileRepository extends JpaRepository<File,String> {


}
