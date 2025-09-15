package edu.ccrm.domain;

public class Instructor extends Person {
    private String instructorDepartment;

    public Instructor(String instructorId, String instructorFullName, String instructorEmail, String instructorDepartment) {
        super(instructorId, instructorFullName, instructorEmail);
        this.instructorDepartment = instructorDepartment;
    }

    public String getDepartment() { return instructorDepartment; }
    public void setDepartment(String instructorDepartment) { this.instructorDepartment = instructorDepartment; }

    @Override
    public String getProfile() {
        return "Instructor [" + super.toString() + ", Dept: " + instructorDepartment + "]";
    }
}