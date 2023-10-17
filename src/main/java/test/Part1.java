package test;

import hk.ust.comp3021.CommonCoreCourse;
import hk.ust.comp3021.EnrollmentSystem;
import hk.ust.comp3021.MajorCourse;
import hk.ust.comp3021.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part1 {
    public static void Task1() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();

        system.parseStudents("student.txt");
        for (int i = 0; i < system.students.size(); i++) {
            if (!Objects.equals(system.students.get(i).studentid, String.format("s10%02d", i+1)))
                throw new AssertionError();
        }

        if (system.students.get(0).completedcourses == null || system.students.get(0).completedcourses.isEmpty())
            throw new AssertionError();
        if (!Objects.equals(system.students.get(5).completedcourses.get(0), "MATH2011"))
            throw new AssertionError();

        system.parseCourses("course.txt");
        String[] commonCore = {"COMP1000", "PHYS1001", "ELEC1001", "MATH1001", "BIEN1000"};
        String[] Major = {"ISDN1002", "COMP2011", "COMP3021", "COMP3021", "PHYS3021"};
        for (String course: commonCore) {
            if (!system.courses.containsKey(course)) throw new AssertionError();
            if (!(system.courses.get(course) instanceof CommonCoreCourse)) throw new AssertionError();
        }
        for (String course: Major) {
            if (!system.courses.containsKey(course)) throw new AssertionError();
            if (!(system.courses.get(course) instanceof MajorCourse)) throw new AssertionError();
        }
    }

    public static void Task2() throws IOException {
        EnrollmentSystem system = new EnrollmentSystem();
        system.parseCourses("course.txt");

        for (int i = 0; i < 10; i++) {
            system.students.add(new Student(""+i, "", 1, 4, new ArrayList<>(List.of("COMP1000")), new ArrayList<>()));
        }
        for (int i = 10; i < 20; i++) {
            system.students.add(new Student(""+i, "", 1, 4, new ArrayList<>(List.of("COMP1000", "PHYS1001")), new ArrayList<>()));
        }
        for (int i = 20; i < 25; i++) {
            system.students.add(new Student(""+i, "", 1, 4, new ArrayList<>(List.of("BIEN1000", "PHYS1001")), new ArrayList<>()));
        }
        system.enrollFirstRound();

        if (system.courses.get("COMP1000").enrolledstudents.size() != 20)
            throw new AssertionError();
        if (system.courses.get("PHYS1001").enrolledstudents.size() != 15)
            throw new AssertionError();
        if (system.courses.get("BIEN1000").enrolledstudents.size() != 5)
            throw new AssertionError();
    }

    public static void main(String[] args) {
        try {
            Task1();
            Task2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}