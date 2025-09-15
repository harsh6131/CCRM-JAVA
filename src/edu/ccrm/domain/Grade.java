package edu.ccrm.domain;

public enum Grade {
    S(10), A(9), B(8), C(7), D(6), E(5), F(0);

    private final int gradePoints;
    Grade(int gradePoints) { this.gradePoints = gradePoints; }
    public int getPoints() { return gradePoints; }
}