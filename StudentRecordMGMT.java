import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    public Student(int studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

class StudentRecordSystem {
    private ArrayList<Student> students;
    private int count;

    public StudentRecordSystem() {
        students = new ArrayList<>();
        count = 0;
    }

    public void addStudent(Student student) {
        students.add(student);
        count++;
    }

    public Student getStudent(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class StudentRecordMGMT {
    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Student by ID");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    system.addStudent(new Student(id, name, age, department));
                    break;
                case 2:
                    System.out.print("Enter Student ID to search: ");
                    int searchID = scanner.nextInt();
                    Student student = system.getStudent(searchID);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    system.displayAllStudents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
