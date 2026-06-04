// Backend Implementation

// 1. Update the AccountRepository to support pagination
package prj.educatrix.main.repository;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import prj.educatrix.main.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Transactional
    Account save(Account account);

    List<Account> findAll();

    // Add pagination support
    Page<Account> findAll(Pageable pageable);

    Optional<Account> findById(String idAccount);

    void deleteAllByIdIn(List<String> account_ids);

    @Modifying
    @Query("DELETE FROM Account a WHERE a.id = :idAccount")
    void deleteById(@Param("idAccount") String idAccount);

    @Query("SELECT a FROM Account a WHERE a.username LIKE %:username% AND a.role.role_id = :roleId")
    List<Account> findByUsernameContainsAndRole_id(@Param("username") String username, @Param("roleId") String roleId);

    @Query("SELECT a FROM Account a WHERE a.username LIKE %:username% AND a.role.role_id = :roleId")
    Page<Account> findByUsernameContainsAndRole_id(@Param("username") String username, @Param("roleId") String roleId, Pageable pageable);

    @Query("SELECT a FROM Account a WHERE a.role.role_id = :roleId")
    List<Account> findByRole_RoleId(@Param("roleId") String roleId);

    @Query("SELECT a FROM Account a WHERE a.role.role_id = :roleId")
    Page<Account> findByRole_RoleId(@Param("roleId") String roleId, Pageable pageable);

    List<Account> findByStatus(String status);

    Page<Account> findByStatus(String status, Pageable pageable);

    @Modifying
    void deleteAllByIdIn(Collection<String> ids);
    Optional<Account> findByUsername(String username);

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.profile LEFT JOIN FETCH a.role " +
            "LEFT JOIN FETCH a.courses LEFT JOIN FETCH a.reviews WHERE a.id = :id")
    Optional<Account> findAccountWithDetailsById(@Param("id") String id);
}