package com.valise.invoice_generator.repositroy;

import com.valise.invoice_generator.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    //boolean existsByMobile(String mobile);
}

