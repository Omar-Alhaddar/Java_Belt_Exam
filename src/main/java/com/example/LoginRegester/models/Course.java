package com.example.LoginRegester.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @NotBlank(message="Course Name must be present")
	 private String name;
	 
	 @NotBlank(message="Instructor Name must be present")
	 private String instructor;
	    
	 @Min(0)
	 private Integer capacity;
	 
	 @Column(updatable=false)
	 private Date createdAt;
	 private Date updatedAt;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "users_courses" , 
	    joinColumns = @JoinColumn(name = "course_id") , 
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	    		)
	    private List<User> joinedUsers;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name= "user_id")
	    private User user;
	   
	    
	    public Course() {
			super();
		}
	    
	    
		public Course(String name, String instructor, Integer capacity, List<User> joinedUsers,User user) {
			super();
			this.name = name;
			this.instructor = instructor;
			this.capacity = capacity;
			this.joinedUsers = joinedUsers;
			this.user = user;
		}
		


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getInstructor() {
			return instructor;
		}


		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}


		public Integer getCapacity() {
			return capacity;
		}


		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}


		public List<User> getJoinedUsers() {
			return joinedUsers;
		}


		public void setJoinedUsers(List<User> joinedUsers) {
			this.joinedUsers = joinedUsers;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	 
}
