package com.web.app.repository;

import com.web.app.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
