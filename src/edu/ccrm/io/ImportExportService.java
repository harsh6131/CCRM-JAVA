package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public class ImportExportService {

    // Imports students from a CSV with columns: id,regNo,fullName,email
    public int importStudents(String studentFilePath, StudentService studentServiceInstance) throws IOException {
        List<String> studentFileLines = Files.readAllLines(Paths.get(studentFilePath));
        int importedStudentCount = 0;
        for (String studentLine : studentFileLines) {
            String[] studentDataParts = studentLine.strip().split(",");
            if (studentDataParts.length >= 4) {
                Student studentRecord = new Student(studentDataParts[0], studentDataParts[1], studentDataParts[2], studentDataParts[3]);
                studentServiceInstance.addStudent(studentRecord);
                importedStudentCount++;
            }
        }
        return importedStudentCount;
    }

    // Exports all current students as CSV
    public void exportStudents(String studentFilePath, StudentService studentServiceInstance) throws IOException {
        List<String> exportedStudentLines = studentServiceInstance.listStudents()
                .stream()
                .map(student -> student.getId() + "," + student.getRegNo() + "," + student.getFullName() + "," + student.getEmail())
                .collect(Collectors.toList());
        Files.write(Paths.get(studentFilePath), exportedStudentLines);
    }

    // Imports courses from CSV: code,title,credits,instructorId,semester,department
    public int importCourses(String courseFilePath, CourseService courseServiceInstance) throws IOException {
        List<String> courseFileLines = Files.readAllLines(Paths.get(courseFilePath));
        int importedCourseCount = 0;
        for (String courseLine : courseFileLines) {
            String[] courseDataParts = courseLine.strip().split(",");
            if (courseDataParts.length >= 6) {
                Course courseRecord = new Course(
                        courseDataParts[0], // code
                        courseDataParts[1], // title
                        Integer.parseInt(courseDataParts[2]), // credits
                        courseDataParts[3], // instructorId
                        Semester.valueOf(courseDataParts[4].toUpperCase()),
                        courseDataParts[5] // department
                );
                courseServiceInstance.addCourse(courseRecord);
                importedCourseCount++;
            }
        }
        return importedCourseCount;
    }

    // Exports courses to CSV
    public void exportCourses(String courseFilePath, CourseService courseServiceInstance) throws IOException {
        List<String> exportedCourseLines = courseServiceInstance.listCourses()
                .stream()
                .map(course -> course.getCode() + "," + course.getTitle() + "," + course.getCredits() + "," +
                        course.getInstructorId() + "," + course.getSemester() + "," + course.getDepartment())
                .collect(Collectors.toList());
        Files.write(Paths.get(courseFilePath), exportedCourseLines);
    }
}