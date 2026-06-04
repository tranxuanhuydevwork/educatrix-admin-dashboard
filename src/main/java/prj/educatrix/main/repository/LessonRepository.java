package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT l FROM Lesson l LEFT JOIN FETCH l.files WHERE l.course.id = :courseId ORDER BY l.position")
    List<Lesson> findByCourseIdOrderByPosition(@Param("courseId") Integer courseId);
    @Query("SELECT l FROM Lesson l LEFT JOIN FETCH l.files LEFT JOIN FETCH l.course WHERE l.id = :id")
    Optional<Lesson> findByIdWithFiles(@Param("id") Integer id);




}
