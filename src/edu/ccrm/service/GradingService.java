package edu.ccrm.service;

import edu.ccrm.domain.*;

public class GradingService {
    public static Grade calculateGrade(int obtainedMarks) {
        if (obtainedMarks >= 90) return Grade.S;
        else if (obtainedMarks >= 80) return Grade.A;
        else if (obtainedMarks >= 70) return Grade.B;
        else if (obtainedMarks >= 60) return Grade.C;
        else if (obtainedMarks >= 50) return Grade.D;
        else if (obtainedMarks >= 40) return Grade.E;
        else return Grade.F;
    }

    public void assignMarks(Student targetStudent, String targetCourseCode, int obtainedMarks) {
        Grade calculatedGrade = calculateGrade(obtainedMarks);
        targetStudent.addTranscriptEntry(targetCourseCode, obtainedMarks, calculatedGrade);
    }

    public double computeGPA(Student targetStudent) {
        if (targetStudent.getTranscript().isEmpty()) return 0.0;
        double totalGradePoints = 0;
        int totalCoursesCount = 0;
        for (TranscriptEntry transcriptEntry : targetStudent.getTranscript().values()) {
            totalGradePoints += transcriptEntry.getGrade().getPoints();
            totalCoursesCount++;
        }
        return (totalCoursesCount == 0) ? 0.0 : totalGradePoints / totalCoursesCount;
    }
}
