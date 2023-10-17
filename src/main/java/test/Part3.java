package test;

import hk.ust.comp3021.EnrollmentSystem;
import hk.ust.comp3021.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part3 {
    public static void Task7() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 10; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("COMP1000")), new ArrayList<>()));
        }
        for (int i = 10; i < 20; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("COMP1000", "PHYS1001")), new ArrayList<>()));
        }
        for (int i = 20; i < 28; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("BIEN1000", "PHYS1001")), new ArrayList<>()));
        }
        for (int i = 28; i < 33; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("PHYS1001")), new ArrayList<>()));
        }
        system.enrollFirstRound();

        if (system.findNumTA() != 4+4+3)
            throw new AssertionError();
    }

    public static void Task8() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        // yes
        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("ELEC1001", "COMP2011", "BIEN1000")), new ArrayList<>()));
        }
        // no
        Student t1 = new Student(""+10, "ECE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        Student t2 = new Student(""+11, "CSE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        Student t4 = new Student(""+13, "CSE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        // yes
        Student t3 = new Student(""+12, "CBE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        system.students.add(t3);
        system.students.add(t4);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (system.findNumAllSuccess() != 10)
            throw new AssertionError();
    }

    public static void Task9() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+i, "", 1, 4.0, new ArrayList<>(List.of("ELEC1001", "COMP2011", "BIEN1000", "MATH1001")), new ArrayList<>()));
        }
        Student t1 = new Student(""+10, "ECE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        Student t2 = new Student(""+11, "CSE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        Student t3 = new Student(""+12, "CBE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        Student t4 = new Student(""+13, "CSE", 1, 4.0, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        system.students.add(t3);
        system.students.add(t4);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (!system.courses.get("ELEC1001").waitlist.contains(t2.studentid))
            throw new AssertionError();
        if (!system.courses.get("COMP2011").waitlist.contains(t1.studentid))
            throw new AssertionError();
        if (!system.courses.get("BIEN1000").waitlist.contains(t4.studentid))
            throw new AssertionError();
        for (int i = 6; i < 9; i++) {
            if (system.courses.get("MATH1001").waitlist.contains(t4.studentid))
                throw new AssertionError();
        }
    }

    public static void main(String[] args) throws IOException {
        Task7();
        Task8();
        Task9();
    }
}
