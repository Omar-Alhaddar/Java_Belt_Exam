package com.example.LoginRegester.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.LoginRegester.models.Course;
import com.example.LoginRegester.models.User;
import com.example.LoginRegester.repositories.CourseRepository;
import com.example.LoginRegester.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepo;
    

    
    public UserService(UserRepository userRepository, CourseRepository courseRepo) {
		super();
		this.userRepository = userRepository;
		this.courseRepo = courseRepo;
	}

	// register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
//    	Optional<User> u = userRepository.findById(id);
//    	
//    	if(u.isPresent()) {
//            return u.get();
//    	} else {
//    	    return null;
//    	}
    	return userRepository.findById(id).orElse(null);
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public void updateUser(User user) {
    	userRepository.save(user);
    } 
    
    
    
    public Course addCourse(Course event) {
    	return courseRepo.save(event);
    }
    
    
    public List<Course> allCourses(){
    	return (List<Course>) courseRepo.findAll();
    }
    
    
    public Course findCourseById(Long id) {
    return courseRepo.findById(id).orElse(null);
    }
    
    public void updateCourse(Course course) {
    	courseRepo.save(course);
    }
    

    public void deleteCourse(Long id) {
    	courseRepo.deleteById(id);
    }
    
}