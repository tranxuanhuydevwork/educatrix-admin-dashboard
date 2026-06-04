package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prj.educatrix.main.domain.Teacher_registration;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Teacher_registration, Integer> {
    List<Teacher_registration> findByStatus(String status);
    List<Teacher_registration> findTeacher_registrationByStatusIn(List<String> status);
    @Query("SELECT t FROM Teacher_registration t WHERE t.account.id = :accountId AND t.status = :status")
    Teacher_registration findTeacherRegistrationByAccountIdAndStatus(@Param("accountId") String accountId,
                                                                     @Param("status") String status);

    List<Teacher_registration> findTeacher_registrationByAccount_Id(String accountId);
    @Query("SELECT t FROM Teacher_registration t WHERE t.account.username LIKE %:username%")
    List<Teacher_registration> findTeacherRegistrationByAccountContaining(@Param("username") String username);
    void deleteByAccountId(String accountId);
}
