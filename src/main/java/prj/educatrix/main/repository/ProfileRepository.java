package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prj.educatrix.main.domain.Profile;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
    Profile findProfileByAccount_Id(String accountId);
}
