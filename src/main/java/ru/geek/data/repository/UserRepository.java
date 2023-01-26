package ru.geek.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geek.data.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
