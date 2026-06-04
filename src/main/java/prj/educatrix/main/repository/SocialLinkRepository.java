package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Social_link;

import java.util.List;

public interface SocialLinkRepository extends JpaRepository<Social_link,Integer> {
    @Query("SELECT s FROM Social_link s WHERE s.profile.account.id = :profileId")
    List<Social_link> findAllByProfileId(@Param("profileId") String profileId);
}
