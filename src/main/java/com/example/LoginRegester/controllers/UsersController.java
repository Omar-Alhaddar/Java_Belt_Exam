package com.example.LoginRegester.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LoginRegester.models.Course;
import com.example.LoginRegester.models.User;
import com.example.LoginRegester.services.UserService;
import com.example.LoginRegester.validation.UserValidator;

@Controller
public class UsersController {
private final UserService userService;
private final UserValidator userValidator;
    
    public UsersController(UserService userService , UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    
    @RequestMapping("/")
    public String root(@ModelAttribute("user") User user) {
    return "index.jsp";
    }
    
    @RequestMapping("admin")
    public String admin(@ModelAttribute("user") User user) {
    return "adminPage.jsp";
    }

    
    @RequestMapping("/login")
    public String login(HttpSession session) {
    	if (session.getAttribute("userID") != null)
    		return "redirect:/home";
        return "dashboard.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
    	userValidator.validate(user, result);
    	if (result.hasErrors()) {
    		return "index.jsp";
    	}
    	if(userService.findByEmail(user.getEmail()) != null) {
    		
    		model.addAttribute("error", "this email is already exist");
    		return "index.jsp";
    	}
    	else {
    		userService.registerUser(user);
    		session.setAttribute("userId", user.getId());
    		return "redirect:/courses";
    	}
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session,@ModelAttribute("user") User user) {
    	if (userService.authenticateUser(email, password)) {
    		session.setAttribute("userId", userService.findByEmail(email).getId());
    		return "redirect:/courses";
    	}
    	else {
    		model.addAttribute("error", "invalid user name or password");
    		return "index.jsp";
    	}
    	
    	
    }
    
    
    
    @GetMapping("/courses")
	public String dashboard(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = userService.findUserById((Long) session.getAttribute("userId"));
		model.addAttribute("user", user);
        List<Course> courses = userService.allCourses();
        model.addAttribute("courses", courses);
		return "dashboard.jsp";
	}
    
    @RequestMapping("/courses/new")
    public String newcourse(@Valid @ModelAttribute("course") Course Course) {

    		return "new.jsp";

    }
    
	@PostMapping("/courses/new")
	public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";
		} else {
			userService.addCourse(course);
			return "redirect:/courses";
		}
	}
	
	@RequestMapping("courses/{id}")
	public String showCourse(@PathVariable("id") Long id, Model model, HttpSession session) {
		Course myCourse = userService.findCourseById(id);
		model.addAttribute("course", myCourse);

		List<User> users = myCourse.getJoinedUsers();
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("User",user);
		return "show.jsp";
	}
    
	@RequestMapping("/courses/add/{id}")
	public String addCourse(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userid = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userid);
		Course course = userService.findCourseById(id);
		
		user.getJoinedcourses().add(course);
		userService.updateUser(user);

		return "redirect:/courses";
	}
	
	@RequestMapping("/courses/{id}/edit")
	public String editPage(@ModelAttribute("course") Course courseObj, @PathVariable("id") Long id, Model model, HttpSession session) {
		Course course = userService.findCourseById(id);
		model.addAttribute("course", course);

		return "editCourse.jsp";
	}
    
	@PostMapping("/courses/update")
	public String updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
		if (result.hasErrors()) {
			return "editCourse.jsp";
		} else {
			List<User> inUsers =course.getJoinedUsers();
			userService.updateCourse(course);
			return "redirect:/courses";
		}
	}
	
	@RequestMapping("/courses/delete/{id}")
	public String deleteCourse(@PathVariable("id") Long id) {
		Course course = userService.findCourseById(id);
		if (course != null) {
			userService.deleteCourse(course.getId());
			return "redirect:/courses";
		} else {
			return "redirect:/courses";
		}

	}
	
	@RequestMapping("/courses/{id}/remove")
	public String removeUser(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		Course course = userService.findCourseById(id);
		
		user.getJoinedcourses().remove(course);
		userService.updateUser(user);
		
		return "redirect:/courses";
	}
	
	@RequestMapping("/courses/{id}/add")
	public String addUserToCourse(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		Course course = userService.findCourseById(id);
		
		user.getJoinedcourses().add(course);
		userService.updateUser(user);
		
		return "redirect:/courses";
	}
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
