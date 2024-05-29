package com.kodilla.finalprojectbackend.repository;


import com.kodilla.finalprojectbackend.communicator.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
