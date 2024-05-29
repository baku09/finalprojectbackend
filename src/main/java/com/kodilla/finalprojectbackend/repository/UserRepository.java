package com.kodilla.finalprojectbackend.repository;


import com.kodilla.finalprojectbackend.communicator.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
