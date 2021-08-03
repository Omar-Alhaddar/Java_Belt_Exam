package com.example.LoginRegester.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginRegester.models.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse,Long>{

}
