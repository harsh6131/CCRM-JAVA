package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService {
    // Map of regNo (or id) to Student object {details}
    private Map<String, Student> studentRegistry = new HashMap<>();

    public void addStudent(Student newStudent) {
        if (studentRegistry.containsKey(newStudent.getRegNo())) {
            System.out.println("Student already exists: " + newStudent.getRegNo());
        } else {
            studentRegistry.put(newStudent.getRegNo(), newStudent);
            System.out.println("Student added: " + newStudent.getRegNo());
        }
    }

    public Student getStudent(String registrationNumber) {
        return studentRegistry.get(registrationNumber);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(studentRegistry.values());
    }

    public void deactivateStudent(String registrationNumber) {
        Student foundStudent = studentRegistry.get(registrationNumber);
        if (foundStudent != null) {
            foundStudent.setActive(false);
            System.out.println("Student deactivated: " + registrationNumber);
        }
    }
}