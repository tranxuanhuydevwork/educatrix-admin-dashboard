package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Course;
import prj.educatrix.main.domain.Reviews;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {
    @Query("SELECT r.rating_stars, COUNT(r) FROM Reviews r GROUP BY r.rating_stars ORDER BY r.rating_stars")
    List<Object[]> getRatingStatistics();

    @Query("SELECT r.course.courseName, COUNT(r) FROM Reviews r GROUP BY r.course.courseName")
    List<Object[]> countReviewsByCourse();

    @Query("SELECT r.course.courseName, AVG(r.rating_stars) FROM Reviews r GROUP BY r.course.courseName")
    List<Object[]> avgRatingByCourse();

    @Query("SELECT r.rating_stars, COUNT(r) FROM Reviews r GROUP BY r.rating_stars")
    List<Object[]> countRatingDistribution();
    @Query("SELECT r.rating_stars, COUNT(r.id) FROM Reviews r WHERE r.course.id = :courseId GROUP BY r.rating_stars")
    List<Object[]> countRatingDistributionCourse(@Param("courseId") int courseId);

    @Query("SELECT c.categoryName, COUNT(r) FROM Reviews r JOIN r.course.categories c GROUP BY c.categoryName")
    List<Object[]> countReviewsByCategory();
    @Query("SELECT r FROM Reviews r WHERE r.course.id = :courseID")
    List<Reviews> findByCourseId(@Param("courseID") int courseId);
}
