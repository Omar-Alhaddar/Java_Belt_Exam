package com.example.LoginRegester.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginRegester.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	
	

}
