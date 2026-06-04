package prj.educatrix.main.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prj.educatrix.main.domain.Category;
import prj.educatrix.main.domain.Course;
import prj.educatrix.main.domain.Lesson;

import java.util.*;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.lessons")
    List<Course> findAllWithLessons();

    @Query("SELECT c FROM Course c JOIN c.categories cat WHERE cat.id IN :categories GROUP BY c HAVING COUNT(DISTINCT cat.categoryName) = :size")
    List<Course> findByCategoriesMatchingAll(@Param("categories") Set<Integer> categories, @Param("size") long size);
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.categories LEFT JOIN FETCH c.lessons l LEFT JOIN FETCH l.files")
    List<Course> findAllWithCategoriesAndLessonsAndFiles();
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.categories LEFT JOIN FETCH c.lessons l LEFT JOIN FETCH l.files WHERE c.id = :courseId")
    Optional<Course> findByIdWithLessonsAndCategories(@Param("courseId") Integer courseId);
    @Query("SELECT c FROM Course c WHERE c.courseName LIKE %:courseName%")
    List<Course> findCoursesByCourseName(@Param("courseName") String courseName);
    @Query("SELECT c FROM Course c WHERE c.teacher.id LIKE :teacher")
    List<Course> findCoursesByTeacher(@Param("teacher") String teacher);



}
