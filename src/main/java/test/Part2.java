package test;

import hk.ust.comp3021.EnrollmentSystem;
import hk.ust.comp3021.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part2 {

    public static void Task3() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, new ArrayList<>(List.of("COMP1000", "PHYS1001")), new ArrayList<>()));
        }
        Student t1 = new Student(""+1, "", 1, 4, new ArrayList<>(List.of("MATH1001", "COMP1000", "PHYS1001", "ELEC1001")), new ArrayList<>());
        Student t2 = new Student(""+2, "", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP1000")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (system.courses.get("PHYS1001").enrolledstudents.contains(t1.studentid))
            throw new AssertionError();
        if (system.courses.get("COMP1000").enrolledstudents.contains(t2.studentid))
            throw new AssertionError();
        if (Objects.equals(system.courses.get("ELEC1001").enrolledstudents.get(0), "1"))
            throw new AssertionError();
        if (Objects.equals(system.courses.get("ELEC1001").enrolledstudents.get(1), "2"))
            throw new AssertionError();
    }

    public static void Task4() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, new ArrayList<>(List.of("ELEC1001", "COMP2011", "BIEN1000")), new ArrayList<>()));
        }
        Student t1 = new Student(""+1, "ECE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        Student t2 = new Student(""+2, "CSE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        Student t3 = new Student(""+3, "CBE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        Student t4 = new Student(""+4, "CSE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        system.students.add(t3);
        system.students.add(t4);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (system.courses.get("ELEC1001").enrolledstudents.contains(t2.studentid))
            throw new AssertionError();
        if (system.courses.get("COMP2011").enrolledstudents.contains(t1.studentid))
            throw new AssertionError();
        if (!system.courses.get("BIEN1000").enrolledstudents.contains(t3.studentid))
            throw new AssertionError();
        if (system.courses.get("BIEN1000").enrolledstudents.contains(t4.studentid))
            throw new AssertionError();
    }

    public static void Task5() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        Student t1 = new Student(""+1, "", 1, 4, new ArrayList<>(List.of("ELEC1001", "PHYS1001", "ISDN1002", "PHYS2011", "MATH2011")), new ArrayList<>(List.of("PHYS1001")));
        Student t2 = new Student(""+2, "", 1, 2, new ArrayList<>(List.of("ELEC1001", "PHYS1001", "MATH1001", "MATH2011", "PHYS2011")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        system.enrollFirstRound();
        system.enrollSecondRound();

        if (!system.courses.get("ISDN1002").enrolledstudents.contains(t1.studentid))
            throw new AssertionError();
        if (!system.courses.get("PHYS2011").enrolledstudents.contains(t1.studentid))
            throw new AssertionError();
        if (system.courses.get("PHYS2011").enrolledstudents.contains(t2.studentid))
            throw new AssertionError();
        if (!system.courses.get("MATH1001").enrolledstudents.contains(t2.studentid))
            throw new AssertionError();
        if (system.courses.get("MATH2011").enrolledstudents.contains(t2.studentid))
            throw new AssertionError();
        if (!system.courses.get("MATH2011").enrolledstudents.contains(t1.studentid))
            throw new AssertionError();
    }

    public static void Task6() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 9; i++) {
            system.students.add(new Student(""+0, "", 1, 4, new ArrayList<>(List.of("ELEC1001", "COMP2011", "BIEN1000")), new ArrayList<>()));
        }
        Student t1 = new Student(""+1, "ECE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        Student t2 = new Student(""+2, "CSE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "ELEC1001", "COMP2011")), new ArrayList<>());
        system.students.add(t1);
        system.students.add(t2);
        Student t3 = new Student(""+3, "CBE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
        Student t4 = new Student(""+4, "CSE", 1, 4, new ArrayList<>(List.of("MATH1001", "PHYS1001", "BIEN1000")), new ArrayList<>());
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
    }

    public static void main(String[] args) throws IOException {
        Task3();
        Task4();
        Task5();
        Task6();
    }
}
