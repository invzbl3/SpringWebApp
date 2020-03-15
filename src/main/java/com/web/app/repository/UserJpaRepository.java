package com.web.app.repository;

import com.web.app.dto.UsersDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UsersDTO, Long> {
    Optional<UsersDTO> findById(Long id);
    UsersDTO findByName(String name);
}