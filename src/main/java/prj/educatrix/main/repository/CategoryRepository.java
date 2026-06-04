package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.courses")
    List<Category> getListCategory();
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.courses cs WHERE cs.id = :courseId")
    List<Category> getListCategoryByCourse(@Param("courseId") int courseId);
    @Query("SELECT c FROM Category c ORDER BY c.categoryName ASC")
    List<Category> findAllOrderByNameAsc();

    @Query("SELECT c FROM Category c LEFT JOIN c.courses WHERE SIZE(c.courses) > 0 GROUP BY c")
    List<Category> findCategoriesWithCourses();

    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Category> searchByName(String keyword);

    @Query("SELECT COUNT(co) FROM Category c JOIN c.courses co WHERE c.id = :categoryId")
    Integer countCoursesByCategoryId(Integer categoryId);
    boolean existsByCategoryNameIgnoreCase(String categoryName);
    List<Category> findByCategoryNameIgnoreCase(String categoryName);
}
