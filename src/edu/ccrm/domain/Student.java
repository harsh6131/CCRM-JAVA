package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private String registrationNumber;
    private List<String> enrolledCourseCodes;

    private Map<String, TranscriptEntry> studentTranscript = new HashMap<>();

    // student details
    public Student(String studentId, String registrationNumber, String studentFullName, String studentEmail) {
        super(studentId, studentFullName, studentEmail);
        this.registrationNumber = registrationNumber;
        this.enrolledCourseCodes = new ArrayList<>();
    }

    public String getRegNo() { return registrationNumber; }
    public List<String> getEnrolledCourses() { return enrolledCourseCodes; }

    public Map<String, TranscriptEntry> getTranscript() { return studentTranscript; }

    public void addTranscriptEntry(String courseCode, int obtainedMarks, Grade courseGrade) {
        studentTranscript.put(courseCode, new TranscriptEntry(courseCode, obtainedMarks, courseGrade));
    }

    public void enroll(String courseCode) {
        if (!enrolledCourseCodes.contains(courseCode))
            enrolledCourseCodes.add(courseCode);
    }

    public void unenroll(String courseCode) {
        enrolledCourseCodes.remove(courseCode);
    }

    @Override
    public String getProfile() {
        return "Student [" + super.toString() + ", RegNo: " + registrationNumber + ", Courses: " + enrolledCourseCodes + "]";
    }
}
