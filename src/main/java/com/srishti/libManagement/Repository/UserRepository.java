package com.srishti.libManagement.Repository;

import com.srishti.libManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}