package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;

public class CourseService {
    private Map<String, Course> courseRegistry = new HashMap<>();

    public void addCourse(Course newCourse) {
        if (courseRegistry.containsKey(newCourse.getCode())) {
            System.out.println("Course already exists: " + newCourse.getCode());
        } else {
            courseRegistry.put(newCourse.getCode(), newCourse);
            System.out.println("Course added: " + newCourse.getCode());
        }
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courseRegistry.values());
    }
}