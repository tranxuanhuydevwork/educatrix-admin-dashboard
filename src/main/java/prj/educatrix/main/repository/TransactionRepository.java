package prj.educatrix.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prj.educatrix.main.domain.Transaction;


import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.order o JOIN FETCH o.courses")
    List<Transaction> findAllWithOrderAndCourses();
    @Query("SELECT t FROM Transaction t WHERE t.transactionId LIKE %:transactionId%")
    List<Transaction> findTransactionByTransactionIdContaining(@Param("transactionId") String transactionId);
    @Query("SELECT FUNCTION('MONTH', i.transactionDate), COUNT(i) FROM Transaction i GROUP BY FUNCTION('MONTH', i.transactionDate) ORDER BY FUNCTION('MONTH', i.transactionDate)")
    List<Object[]> countInvoicesPerMonth();
    @Query(value = "SELECT * FROM monthly_revenue", nativeQuery = true)
    List<Object[]> getMonthlyRevenue();


}
