package com.sc.exam.sbb.user;

import com.sc.exam.sbb.RepositoryUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long>, RepositoryUtil {
  boolean existsByUsername(String username);

  Optional<SiteUser> findByUsername(String username);

  @Transactional
  @Modifying
  @Query(value = "TRUNCATE TABLE sbb.siteuser cascade", nativeQuery = true)
  // @Query(value = "ALTER SEQUENCE sbb.site_user RESTART WITH 1", nativeQuery = true)
  void truncate();
}
