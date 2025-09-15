package edu.ccrm.domain;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int courseCredits;
    private String courseInstructorId;
    private Semester courseSemester;
    private String courseDepartment;

    public Course(String courseCode, String courseTitle, int courseCredits, String courseInstructorId, Semester courseSemester, String courseDepartment) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCredits = courseCredits;
        this.courseInstructorId = courseInstructorId;
        this.courseSemester = courseSemester;
        this.courseDepartment = courseDepartment;
    }

    // course details

    public String getCode() { return courseCode; }
    public String getTitle() { return courseTitle; }
    public int getCredits() { return courseCredits; }
    public String getInstructorId() { return courseInstructorId; }
    public Semester getSemester() { return courseSemester; }
    public String getDepartment() { return courseDepartment; }

    public void setTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public void setCredits(int courseCredits) { this.courseCredits = courseCredits; }
    public void setInstructorId(String courseInstructorId) { this.courseInstructorId = courseInstructorId; }
    public void setSemester(Semester courseSemester) { this.courseSemester = courseSemester; }
    public void setDepartment(String courseDepartment) { this.courseDepartment = courseDepartment; }

    @Override
    public String toString() {
        return String.format("Course [%s: %s, Credits: %d, Instructor: %s, Semester: %s, Dept: %s]",
                courseCode, courseTitle, courseCredits, courseInstructorId, courseSemester, courseDepartment);
    }
}