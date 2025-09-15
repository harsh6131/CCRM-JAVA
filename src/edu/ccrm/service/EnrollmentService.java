package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;

public class EnrollmentService {
    public boolean enrollStudentInCourse(Student targetStudent, Course targetCourse) {
        if (targetStudent != null && targetCourse != null) {
            // Could add business rules (e.g., max credits) here
            targetStudent.enroll(targetCourse.getCode());
            return true;
        }
        return false;
    }

    public boolean unenrollStudentFromCourse(Student targetStudent, Course targetCourse) {
        if (targetStudent != null && targetCourse != null) {
            targetStudent.unenroll(targetCourse.getCode());
            return true;
        }
        return false;
    }
}