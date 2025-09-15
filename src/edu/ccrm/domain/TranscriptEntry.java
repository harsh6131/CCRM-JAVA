package edu.ccrm.domain;

public class TranscriptEntry {
    private String transcriptCourseCode;
    private int obtainedMarks;
    private Grade obtainedGrade;

    // transcript for grades
    public TranscriptEntry(String transcriptCourseCode, int obtainedMarks, Grade obtainedGrade) {
        this.transcriptCourseCode = transcriptCourseCode;
        this.obtainedMarks = obtainedMarks;
        this.obtainedGrade = obtainedGrade;
    }

    public String getCourseCode() { return transcriptCourseCode; }
    public int getMarks() { return obtainedMarks; }
    public Grade getGrade() { return obtainedGrade; }

    @Override
    public String toString() {
        return String.format("Course: %s, Marks: %d, Grade: %s", transcriptCourseCode, obtainedMarks, obtainedGrade);
    }
}