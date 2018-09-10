package com.granite.banking.repositories;

import com.granite.banking.model.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Long> {

    Optional<UserLogin> findByEmail(String email);
}
