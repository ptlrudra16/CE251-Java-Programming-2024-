import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GradingSystemMGTM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem system = new GradingSystem();

        // Add sample course credits
        system.addCourseCredits(101, 3);
        system.addCourseCredits(102, 4);
        system.addCourseCredits(103, 2);

        // Sample menu
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Calculate GPA");
            System.out.println("4. Print Grade Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    system.addStudent(new Student(studentID, name));
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Grade (A/B/C/D/F): ");
                    char grade = scanner.next().charAt(0);
                    system.addGrade(new Grade(studentID, courseID, grade));
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    double gpa = system.calculateGPA(studentID);
                    System.out.printf("GPA: %.2f%n", gpa);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    system.printGradeReport(studentID);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class GradingSystem {
    private List<Student> students = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private Map<Integer, Integer> courseCredits = new HashMap<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public void addCourseCredits(int courseID, int credits) {
        courseCredits.put(courseID, credits);
    }

    public double calculateGPA(int studentID) {
        int totalCredits = 0;
        int totalPoints = 0;

        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                int courseID = grade.getCourseID();
                int credits = courseCredits.getOrDefault(courseID, 0);
                int points = gradeToPoints(grade.getGrade());

                totalCredits += credits;
                totalPoints += points * credits;
            }
        }

        return totalCredits == 0 ? 0 : (double) totalPoints / totalCredits;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }

    public void printGradeReport(int studentID) {
        Student student = students.stream()
                .filter(s -> s.getStudentID() == studentID)
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Grade Report for " + student.getName() + ":");
        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                System.out.println("Course ID: " + grade.getCourseID() + ", Grade: " + grade.getGrade());
            }
        }

        double gpa = calculateGPA(studentID);
        System.out.printf("GPA: %.2f%n", gpa);
    }
}

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}
