package prj.educatrix.main.repository;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prj.educatrix.main.domain.AccountStatusHistory;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountStatusHistoryRepository extends JpaRepository<AccountStatusHistory, Integer> {
    List<AccountStatusHistory> findAll();
    @Query("SELECT ah FROM AccountStatusHistory ah WHERE ah.account.id =:accountId ORDER BY ah.changeTime DESC")
    List<AccountStatusHistory> findAccountStatusHistoryByAccountId( @Param("accountId") String accountId);
//
//    Collection<Object> findByAccountIdOrderByDateDesc(String accountId);
//
//    Remapper findFirstByAccountIdAndEventOrderByDateAsc(String accountId, String accountCreated);
}
