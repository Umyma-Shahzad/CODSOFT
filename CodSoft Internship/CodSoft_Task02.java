import java.util.Scanner;

class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalSubjects;
        int totalMarks = 0;
        double averagePercentage;
        String grade;

     
        do {
            System.out.print("Enter the total number of subjects: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number of subjects.");
                scanner.next(); 
            }
            totalSubjects = scanner.nextInt();
        } while (totalSubjects <= 0);

       
        for (int i = 1; i <= totalSubjects; i++) {
            int marks;
            do {
                System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid marks value.");
                    scanner.next();
                }
                marks = scanner.nextInt();
            } while (marks < 0 || marks > 100);

            totalMarks += marks;
        }
        averagePercentage = (double) totalMarks / totalSubjects;
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
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        scanner.close();
    }
}
