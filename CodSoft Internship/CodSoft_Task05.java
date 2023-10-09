import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (registeredCourses.size() < 5) { // Maximum 5 courses per student
            registeredCourses.add(course);
            System.out.println("Course registration successful.");
        } else {
            System.out.println("Cannot register for more than 5 courses.");
        }
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        System.out.println("Course dropped successfully.");
    }
}

class CourseRegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;

    public CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseDatabase.add(course);
    }

    public void addStudent(Student student) {
        studentDatabase.add(student);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase) {
            int availableSlots = course.getCapacity() - getCourseRegistrations(course).size();
            System.out.println("Course Code: " + course.getCode() +
                    ", Title: " + course.getTitle() +
                    ", Available Slots: " + availableSlots +
                    ", Schedule: " + course.getSchedule());
        }
    }

    public List<Student> getStudentDatabase() {
        return studentDatabase;
    }

    public List<Course> getCourseDatabase() {
        return courseDatabase;
    }

    public List<Student> getCourseRegistrations(Course course) {
        List<Student> registrations = new ArrayList<>();
        for (Student student : studentDatabase) {
            if (student.getRegisteredCourses().contains(course)) {
                registrations.add(student);
            }
        }
        return registrations;
    }
}

class StudentCousreRegistrationSystem {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Adding courses to the system
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 50,
                "Mon/Wed/Fri 10:00 AM");
        Course course2 = new Course("MATH201", "Calculus I", "Limits, derivatives, and integrals", 40,
                "Tue/Thu 2:00 PM");
        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);

        // Adding students to the system
        Student student1 = new Student("S001", "John Doe");
        Student student2 = new Student("S002", "Sarah Jane");
        registrationSystem.addStudent(student1);
        registrationSystem.addStudent(student2);

        Scanner scanner = new Scanner(System.in);

        // Course registration simulation
        while (true) {
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registrationSystem.displayCourseListing();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();

                    Student student = null;
                    Course course = null;

                    for (Student s : registrationSystem.getStudentDatabase()) {
                        if (s.getStudentId().equals(studentId)) {
                            student = s;
                            break;
                        }
                    }

                    for (Course c : registrationSystem.getCourseDatabase()) {
                        if (c.getCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }

                    if (student != null && course != null) {
                        student.registerForCourse(course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentIdDrop = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseCodeDrop = scanner.next();

                    Student studentDrop = null;
                    Course courseDrop = null;

                    for (Student s : registrationSystem.getStudentDatabase()) {
                        if (s.getStudentId().equals(studentIdDrop)) {
                            studentDrop = s;
                            break;
                        }
                    }

                    for (Course c : registrationSystem.getCourseDatabase()) {
                        if (c.getCode().equals(courseCodeDrop)) {
                            courseDrop = c;
                            break;
                        }
                    }

                    if (studentDrop != null && courseDrop != null) {
                        studentDrop.dropCourse(courseDrop);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
