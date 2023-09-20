package test;

import hk.ust.comp3021.EnrollmentSystem;
import hk.ust.comp3021.Student;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Part2 {

    public static void Task3() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, List.of("COMP1000", "PHYS1001"), null));
        }
        Student t1 = new Student(""+1, "", 1, 4, List.of("MATH1001", "COMP1000", "PHYS1001", "ELEC1001"), null);
        Student t2 = new Student(""+2, "", 1, 4, List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP1000"), null);
        system.students.add(t1);
        system.students.add(t2);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (system.courses.get("PHYS1001").isStudentEnrolled(t1))
            throw new AssertionError();
        if (system.courses.get("COMP1000").isStudentEnrolled(t2))
            throw new AssertionError();
        if (Objects.equals(system.courses.get("ELEC1001").getEnrolledStudents().get(0), "1"))
            throw new AssertionError();
        if (Objects.equals(system.courses.get("ELEC1001").getEnrolledStudents().get(1), "2"))
            throw new AssertionError();
    }

    public static void Task4() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, List.of("ELEC1001", "COMP2011", "BIEN1000"), null));
        }
        Student t1 = new Student(""+1, "ECE", 1, 4, List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011"), null);
        Student t2 = new Student(""+2, "CSE", 1, 4, List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011"), null);
        system.students.add(t1);
        system.students.add(t2);
        Student t3 = new Student(""+3, "CBE", 1, 4, List.of("MATH1001", "PHYS1001", "BIEN1000"), null);
        Student t4 = new Student(""+4, "CSE", 1, 4, List.of("MATH1001", "PHYS1001", "BIEN1000"), null);
        system.students.add(t3);
        system.students.add(t4);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (system.courses.get("ELEC1001").isStudentEnrolled(t2))
            throw new AssertionError();
        if (system.courses.get("COMP2011").isStudentEnrolled(t1))
            throw new AssertionError();
        if (!system.courses.get("BIEN1000").isStudentEnrolled(t3))
            throw new AssertionError();
        if (system.courses.get("BIEN1000").isStudentEnrolled(t4))
            throw new AssertionError();
    }

    public static void Task5() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        Student t1 = new Student(""+1, "", 1, 4, List.of("ELEC1001", "PHYS1001", "ISDN1002", "PHYS2011", "MATH2011"), List.of("PHYS1001"));
        Student t2 = new Student(""+2, "", 1, 2, List.of("ELEC1001", "PHYS1001", "MATH1001", "MATH2011", "PHYS2011"), null);
        system.students.add(t1);
        system.students.add(t2);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (!system.courses.get("ISDN1002").isStudentEnrolled(t1))
            throw new AssertionError();
        if (!system.courses.get("PHYS2011").isStudentEnrolled(t1))
            throw new AssertionError();
        if (system.courses.get("PHYS2011").isStudentEnrolled(t2))
            throw new AssertionError();
        if (!system.courses.get("MATH1001").isStudentEnrolled(t2))
            throw new AssertionError();
        if (system.courses.get("MATH2011").isStudentEnrolled(t2))
            throw new AssertionError();
        if (!system.courses.get("MATH2011").isStudentEnrolled(t1))
            throw new AssertionError();
    }

    public static void Task6() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, List.of("ELEC1001", "COMP2011", "BIEN1000"), null));
        }
        Student t1 = new Student(""+1, "ECE", 1, 4, List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011"), null);
        Student t2 = new Student(""+2, "CSE", 1, 4, List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011"), null);
        system.students.add(t1);
        system.students.add(t2);
        Student t3 = new Student(""+3, "CBE", 1, 4, List.of("MATH1001", "PHYS1001", "BIEN1000"), null);
        Student t4 = new Student(""+4, "CSE", 1, 4, List.of("MATH1001", "PHYS1001", "BIEN1000"), null);
        system.students.add(t3);
        system.students.add(t4);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (!system.courses.get("ELEC1001").getWaitlist().contains(t2.getStudentID()))
            throw new AssertionError();
        if (!system.courses.get("COMP2011").getWaitlist().contains(t1.getStudentID()))
            throw new AssertionError();
        if (!system.courses.get("BIEN1000").getWaitlist().contains(t4.getStudentID()))
            throw new AssertionError();
    }

    public static void main(String[] args) throws IOException {
        Task3();
        Task4();
        Task5();
        Task6();
    }
}
