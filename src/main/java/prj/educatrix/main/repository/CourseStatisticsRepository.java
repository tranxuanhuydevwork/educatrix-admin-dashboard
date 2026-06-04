package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Course_Statistics;

import java.util.Date;
import java.util.List;

public interface CourseStatisticsRepository extends JpaRepository<Course_Statistics,Integer> {

    @Query("SELECT AVG(cs.completionRate) FROM Course_Statistics cs")
    Double getAverageCompletionRate();
    @Query("SELECT cs.completionRate FROM Course_Statistics cs WHERE cs.course.id = :courseId")
    Double getAverageCompletionRateByCourseId(@Param("courseId") Integer courseId);


    @Query("SELECT AVG(cs.dropOutRate) FROM Course_Statistics cs")
    Double getAverageDropOutRate();

    @Query("SELECT SUM(cs.numEnrolledStudent) FROM Course_Statistics cs")
    Integer getTotalStudents();

    @Query("SELECT SUM(cs.numEnrolledStudent) FROM Course_Statistics cs WHERE cs.course.id = :courseId")
    Integer getTotalStudentsCourse(@Param("courseId") int courseId);

    @Query("SELECT SUM(cs.revenue) FROM Course_Statistics cs WHERE cs.course.id = :courseId")
    Integer getTotalRevenueCourse(@Param("courseId") int courseId);

    @Query("SELECT cs FROM Course_Statistics cs WHERE cs.period = :period AND cs.courseId = :courseId ORDER BY cs.recordedAt DESC")
    List<Course_Statistics> findLatestByPeriodAndCourseId(@Param("period") String period, @Param("courseId") Integer courseId, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT * FROM course_statistics cs WHERE cs.period = :period AND cs.course_id = :courseId ORDER BY cs.recorded_at DESC LIMIT :limit", nativeQuery = true)
    List<Course_Statistics> findLatestByPeriodAndCourseIdNative(@Param("period") String period, @Param("courseId") Integer courseId, @Param("limit") int limit);

    @Query("SELECT cs FROM Course_Statistics cs WHERE cs.period = :period AND cs.courseId = :courseId AND cs.recordedAt < :date ORDER BY cs.recordedAt DESC")
    List<Course_Statistics> findPreviousByPeriodAndCourseIdAndDate(@Param("period") String period, @Param("courseId") Integer courseId, @Param("date") Date date, org.springframework.data.domain.Pageable pageable);

    // OR native SQL approach
    @Query(value = "SELECT * FROM course_statistics cs WHERE cs.period = :period AND cs.course_id = :courseId AND cs.recorded_at < :date ORDER BY cs.recorded_at DESC LIMIT :limit", nativeQuery = true)
    List<Course_Statistics> findPreviousByPeriodAndCourseIdAndDateNative(@Param("period") String period, @Param("courseId") Integer courseId, @Param("date") Date date, @Param("limit") int limit);


    @Query(value = "SELECT cs.completion_rate FROM course_statistics cs WHERE cs.course_id = :courseId AND cs.period = :period ORDER BY cs.recorded_at DESC LIMIT 1", nativeQuery = true)
    Double getCompletionRateByCourseId(@Param("courseId") Integer courseId, @Param("period") String period);

    @Query("SELECT cs FROM Course_Statistics cs WHERE cs.courseId = :courseId ORDER BY cs.recordedAt DESC")
    List<Course_Statistics> findLatestByCourseId(@Param("courseId") Integer courseId, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT * FROM course_statistics cs WHERE cs.course_id = :courseId ORDER BY cs.recorded_at DESC LIMIT :limit", nativeQuery = true)
    List<Course_Statistics> findLatestByCourseIdNative(@Param("courseId") Integer courseId, @Param("limit") int limit);
}