import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("     STUDENT GRADE CALCULATOR");
        System.out.println("--------------------------------");
        System.out.print("Enter the number of subjects: ");
        int subjects = sc.nextInt();
        while (subjects <= 0) {
            System.out.print("Please enter a valid number of subjects: ");
            subjects = sc.nextInt();
        }
        int[] marks = new int[subjects];
        int totalMarks = 0;
        for (int i = 0; i < subjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (0-100): ");
            marks[i] = sc.nextInt();
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks! Enter marks between 0 and 100: ");
                marks[i] = sc.nextInt();
            }
            totalMarks += marks[i];
        }
        double averagePercentage = (double) totalMarks / subjects;
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }
        System.out.println("\n------------------------------");
        System.out.println("             RESULT");
        System.out.println("--------------------------------");
        System.out.println("Number of Subjects : " + subjects);
        System.out.println("Total Marks        : " + totalMarks + "/" + (subjects * 100));
        System.out.printf("Average Percentage : %.2f%%\n", averagePercentage);
        System.out.println("Grade Obtained     : " + grade);
        if (grade.equals("F")) {
            System.out.println("Result Status      : FAIL");
        } else {
            System.out.println("Result Status      : PASS");
        }
        System.out.println("--------------------------------");
        sc.close();
    }
}
