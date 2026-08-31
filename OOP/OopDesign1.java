package OOP;
import java.util.*;

class Student {

    private int rollNumber;
    private String name;
    private double marks;
    private double cgpa;
    private String grade;

    Student(int rollNumber, String name, double marks, double cgpa) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
        this.cgpa = cgpa;
        calculateGrade();
    }

    void calculateGrade() {

        if (marks >= 90) {
            grade = "S";
        } else if (marks >= 80) {
            grade = "A";
        } else if (marks >= 70) {
            grade = "B";
        } else if (marks >= 60) {
            grade = "C";
        } else {
            grade = "F";
        }
    }

    int getRollNumber() {
        return rollNumber;
    }

    String getName() {
        return name;
    }

    double getMarks() {
        return marks;
    }

    double getCgpa() {
        return cgpa;
    }

    String getGrade() {
        return grade;
    }

    void display() {
        System.out.println(
            rollNumber + " " +
            name + " " +
            marks + " " +
            cgpa + " " +
            grade
        );
    }
}

class StudentManager {

    private Map<String, List<Student>> studentsByBranch;

    StudentManager() {
        studentsByBranch = new HashMap<>();
    }

    void addStudent(String branch, Student student) {

        if (!studentsByBranch.containsKey(branch)) {
            studentsByBranch.put(branch, new ArrayList<>());
        }

        studentsByBranch.get(branch).add(student);
    }

    void displayBranchWise() {

        for (Map.Entry<String, List<Student>> entry : studentsByBranch.entrySet()) {

            System.out.println("Branch: " + entry.getKey());

            for (Student student : entry.getValue()) {
                student.display();
            }

            System.out.println();
        }
    }

    void sortByGrade() {

        for (Map.Entry<String, List<Student>> entry : studentsByBranch.entrySet()) {

            List<Student> students = entry.getValue();

            students.sort(
                (s1, s2) -> s1.getGrade().compareTo(s2.getGrade())
            );

            System.out.println("Branch: " + entry.getKey());

            for (Student student : students) {
                student.display();
            }

            System.out.println();
        }
    }

    void sortByMarks() {

        for (Map.Entry<String, List<Student>> entry : studentsByBranch.entrySet()) {

            List<Student> students = entry.getValue();

            students.sort(
                (s1, s2) -> Double.compare(
                    s1.getMarks(),
                    s2.getMarks()
                )
            );

            System.out.println("Branch: " + entry.getKey());

            for (Student student : students) {
                student.display();
            }

            System.out.println();
        }
    }

    int countSGradeStudents(String branch) {

        List<Student> students = studentsByBranch.get(branch);

        if (students == null) {
            return 0;
        }

        int count = 0;

        for (Student student : students) {

            if (student.getGrade().equals("S")) {
                count++;
            }
        }

        return count;
    }

    String branchWithHighestSGrades() {

        int maxS = -1;
        String bestBranch = "";

        for (Map.Entry<String, List<Student>> entry : studentsByBranch.entrySet()) {

            int countS = 0;

            for (Student student : entry.getValue()) {

                if (student.getGrade().equals("S")) {
                    countS++;
                }
            }

            if (countS > maxS) {
                maxS = countS;
                bestBranch = entry.getKey();
            }
        }

        return bestBranch;
    }
}

public class OopDesign1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManager manager = new StudentManager();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter branch: ");
            String branch = sc.next();

            System.out.print("Enter roll number: ");
            int rollNumber = sc.nextInt();

            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter marks: ");
            double marks = sc.nextDouble();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();

            Student student = new Student(
                rollNumber,
                name,
                marks,
                cgpa
            );

            manager.addStudent(branch, student);
        }

        System.out.println("\n1. Students Branch Wise");
        manager.displayBranchWise();

        System.out.println("\n2. Students Branch Wise Sorted By Grade");
        manager.sortByGrade();

        System.out.println("\n3. Students Branch Wise Sorted By Marks");
        manager.sortByMarks();

        System.out.print("\nEnter branch to count S grade students: ");
        String branch = sc.next();

        int count = manager.countSGradeStudents(branch);

        System.out.println(
            "Number of S grade students in " + branch + ": " + count
        );

        System.out.println(
            "\nBranch with highest number of S grades: " +
            manager.branchWithHighestSGrades()
        );

        sc.close();
    }
}