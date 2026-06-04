package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prj.educatrix.main.domain.Account;
import prj.educatrix.main.domain.Notification;
import prj.educatrix.main.domain.Notification.NotificationStatus;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(Account user);
    List<Notification> findByUserAndStatusOrderByCreatedAtDesc(Account user, NotificationStatus status);
    List<Notification> findByStatusOrderByCreatedAtDesc(NotificationStatus status);
    List<Notification> findAllByOrderByCreatedAtDesc();
}