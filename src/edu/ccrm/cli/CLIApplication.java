package edu.ccrm.cli;

//student
import edu.ccrm.service.StudentService;
import edu.ccrm.domain.Student;
//courses
import edu.ccrm.service.CourseService;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.service.EnrollmentService;
//grades
import edu.ccrm.service.GradingService;
import edu.ccrm.domain.TranscriptEntry;
//io
import edu.ccrm.io.ImportExportService;
import java.io.IOException;
//utility
import edu.ccrm.util.BackupUtility;
import java.nio.file.Paths;

import java.util.Scanner;

public class CLIApplication {
    private StudentService studentService = new StudentService();
    private CourseService courseService = new CourseService();
    private EnrollmentService enrollmentService = new EnrollmentService();
    private GradingService gradingService = new GradingService();
    private ImportExportService importExportService = new ImportExportService();

    private Scanner inputScanner = new Scanner(System.in);

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("==== Main Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Course");
            System.out.println("4. List Courses");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. View Student Enrollments");
            System.out.println("7. Assign Grade/Marks");
            System.out.println("8. View Student Transcript");
            System.out.println("9. Import Students from CSV");
            System.out.println("10. Export Students to CSV");
            System.out.println("11. Import Courses from CSV");
            System.out.println("12. Export Courses to CSV");
            System.out.println("13. Backup Data Directory");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            int menuChoice = inputScanner.nextInt();
            inputScanner.nextLine();

            switch (menuChoice) {
                case 0: isRunning = false; break;
                case 1: addStudentMenu(); break;
                case 2: listStudentsMenu(); break;
                case 3: addCourseMenu(); break;
                case 4: listCoursesMenu(); break;
                case 5: enrollStudentMenu(); break;
                case 6: viewStudentEnrollmentsMenu(); break;
                case 7: assignGradeMenu(); break;
                case 8: viewTranscriptMenu(); break;
                case 9: importStudentsMenu(); break;
                case 10: exportStudentsMenu(); break;
                case 11: importCoursesMenu(); break;
                case 12: exportCoursesMenu(); break;
                case 13: backupDataMenu(); break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void addStudentMenu() {
        System.out.print("Enter ID: ");
        String studentId = inputScanner.nextLine();
        System.out.print("Enter RegNo: ");
        String registrationNumber = inputScanner.nextLine();
        System.out.print("Enter name: ");
        String studentName = inputScanner.nextLine();
        System.out.print("Enter email: ");
        String studentEmail = inputScanner.nextLine();

        Student newStudent = new Student(studentId, registrationNumber, studentName, studentEmail);
        studentService.addStudent(newStudent);
    }

    private void listStudentsMenu() {
        System.out.println("=== All Students ===");
        for (Student student : studentService.listStudents()) {
            System.out.println(student.getProfile());
        }
    }

    private void addCourseMenu() {
        System.out.print("Enter code: ");
        String courseCode = inputScanner.nextLine();
        System.out.print("Enter title: ");
        String courseTitle = inputScanner.nextLine();
        System.out.print("Enter credits (integer): ");
        int courseCredits = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume leftover newline
        System.out.print("Enter instructorId (optional): ");
        String instructorId = inputScanner.nextLine();
        System.out.print("Enter semester (SPRING, SUMMER, FALL): ");
        String semesterInput = inputScanner.nextLine().toUpperCase();
        Semester selectedSemester = Semester.valueOf(semesterInput);
        System.out.print("Enter department: ");
        String departmentName = inputScanner.nextLine();

        Course newCourse = new Course(courseCode, courseTitle, courseCredits, instructorId, selectedSemester, departmentName);
        courseService.addCourse(newCourse);
    }

    private void listCoursesMenu() {
        System.out.println("=== All Courses ===");
        for (Course course : courseService.listCourses()) {
            System.out.println(course);
        }
    }

    private void enrollStudentMenu() {
        System.out.print("Enter Student RegNo: ");
        String registrationNumber = inputScanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = inputScanner.nextLine();

        Student foundStudent = studentService.getStudent(registrationNumber);
        Course foundCourse = courseService.listCourses().stream()
                .filter(course -> course.getCode().equals(courseCode))
                .findFirst().orElse(null);

        if (foundStudent == null) {
            System.out.println("Student not found: " + registrationNumber);
        } else if (foundCourse == null) {
            System.out.println("Course not found: " + courseCode);
        } else {
            boolean enrollmentSuccess = enrollmentService.enrollStudentInCourse(foundStudent, foundCourse);
            if (enrollmentSuccess) {
                System.out.println("Enrollment successful!");
            } else {
                System.out.println("Enrollment failed.");
            }
        }
    }

    private void viewStudentEnrollmentsMenu() {
        System.out.print("Enter Student RegNo: ");
        String registrationNumber = inputScanner.nextLine();

        Student foundStudent = studentService.getStudent(registrationNumber);
        if (foundStudent == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(foundStudent.getProfile());
        }
    }

    private void assignGradeMenu() {
        System.out.print("Enter Student RegNo: ");
        String registrationNumber = inputScanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = inputScanner.nextLine();
        System.out.print("Enter Marks (0–100): ");
        int obtainedMarks = inputScanner.nextInt();
        inputScanner.nextLine();

        Student foundStudent = studentService.getStudent(registrationNumber);
        if (foundStudent == null) {
            System.out.println("Student not found: " + registrationNumber);
            return;
        }
        if (!foundStudent.getEnrolledCourses().contains(courseCode)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }
        gradingService.assignMarks(foundStudent, courseCode, obtainedMarks);
        System.out.println("Marks and grade assigned.");
    }

    private void viewTranscriptMenu() {
        System.out.print("Enter Student RegNo: ");
        String registrationNumber = inputScanner.nextLine();

        Student foundStudent = studentService.getStudent(registrationNumber);
        if (foundStudent == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("=== Transcript for " + foundStudent.getFullName() + " ===");
            for (TranscriptEntry entry : foundStudent.getTranscript().values()) {
                System.out.println(entry);
            }
            double calculatedGPA = gradingService.computeGPA(foundStudent);
            System.out.printf("GPA: %.2f\n", calculatedGPA);
        }
    }

    private void importStudentsMenu() {
        System.out.print("Enter file path for CSV: ");
        String filePath = inputScanner.nextLine();
        try {
            int importedCount = importExportService.importStudents(filePath, studentService);
            System.out.println("Imported " + importedCount + " students.");
        } catch (IOException e) {
            System.out.println("Failed to import students: " + e.getMessage());
        }
    }

    private void exportStudentsMenu() {
        System.out.print("Enter file path to save CSV: ");
        String filePath = inputScanner.nextLine();
        try {
            importExportService.exportStudents(filePath, studentService);
            System.out.println("Exported students.");
        } catch (IOException e) {
            System.out.println("Failed to export students: " + e.getMessage());
        }
    }

    private void importCoursesMenu() {
        System.out.print("Enter file path for courses CSV: ");
        String filePath = inputScanner.nextLine();
        try {
            int importedCount = importExportService.importCourses(filePath, courseService);
            System.out.println("Imported " + importedCount + " courses.");
        } catch (IOException e) {
            System.out.println("Failed to import courses: " + e.getMessage());
        }
    }

    private void exportCoursesMenu() {
        System.out.print("Enter file path to save courses CSV: ");
        String filePath = inputScanner.nextLine();
        try {
            importExportService.exportCourses(filePath, courseService);
            System.out.println("Exported courses.");
        } catch (IOException e) {
            System.out.println("Failed to export courses: " + e.getMessage());
        }
    }

    private void backupDataMenu() {
        System.out.print("Enter source directory to backup: ");
        String sourceDirectory = inputScanner.nextLine();
        System.out.print("Enter backup target directory: ");
        String destinationDirectory = inputScanner.nextLine();
        try {
            BackupUtility.backupDirectory(Paths.get(sourceDirectory), Paths.get(destinationDirectory));
            System.out.println("Backup complete!");
        } catch (Exception e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }
}
