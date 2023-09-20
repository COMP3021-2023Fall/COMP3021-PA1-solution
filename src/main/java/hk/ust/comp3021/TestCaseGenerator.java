package hk.ust.comp3021;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestCaseGenerator {
    public static String[] departments = {"CSE", "ECE", "MAE", "CBE", "ISD", "SHSS", "IPP", "MATH", "PHYS"};
    public static String[] courses = {"COMP", "ELEC", "MECH", "BIEN", "ISDN", "SOSC", "PPOL", "MATH", "PHYS"};
    public static String[] coursesCode = {"1000", "2000", "3000", "1001", "2010", "3010", "2011", "1002", "3021"};
    public static String[] coursesType = {"Major", "CommonCore"};

    public static List<String> generatedCourses = new ArrayList<>();

    public static void writeStudents(String fileName, int num) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            int count = 0;
            Random rn = new Random();
            while ( num - count++ > 0 ) {
                String studentID = "s" + 1000 + count;
                String department = departments[rn.nextInt(departments.length)];
                int yearOfStudy = rn.nextInt(4)+1;
                double CGA = rn.nextDouble(2.0, 4.3);

                List<String> preferences = new ArrayList<>();
                int numP = rn.nextInt(10);
                for (int p = 0; p < numP; p++) {
                    String randomCourse = generatedCourses.get(rn.nextInt(courses.length));
                    if (!preferences.contains(randomCourse)){
                        preferences.add(randomCourse);
                    }
                }

                List<String> completedCourses = new ArrayList<>();
                int numC = rn.nextInt(10);
                for (int p = 0; p < numC; p++) {
                    String randomCourse = generatedCourses.get(rn.nextInt(courses.length));
                    if (!completedCourses.contains(randomCourse)){
                        completedCourses.add(randomCourse);
                    }
                }

                bw.write(studentID + ", " +
                        department + ", " +
                        yearOfStudy + ", " +
                        String.format("%.2f", CGA) + ", " +
                        "[" + String.join(" ", preferences)+ "], " +
                        "[" + String.join(" ", completedCourses)+ "]"
                );
                bw.newLine();
            }
        }
    }

    public static void writeCourses(String fileName, int num) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            int count = 0;
            Random rn = new Random();
            while ( num - count++ > 0 ) {
                int nCourse = rn.nextInt(courses.length);
                String courseID = courses[nCourse]+coursesCode[rn.nextInt(coursesCode.length)];
                generatedCourses.add(courseID);
                String department = departments[nCourse];

                int nType = rn.nextInt(coursesType.length);
                String courseType = coursesType[nType];
                String isHonor = "NA";
                String prerequisitesStr = "NA";

                if (nType == 0) { // major
                    List<String> prerequisites = new ArrayList<>();
                    int nP = rn.nextInt(2);
                    for (int p = 0; p < nP; p++) {
                        prerequisites.add(courses[nType]+coursesCode[rn.nextInt(coursesCode.length)]);
                    }
                    prerequisitesStr = "["+ String.join(" ", prerequisites)+"]";
                } else { // common core
                    isHonor = "" + rn.nextBoolean();
                }

                bw.write(courseID + ", " +
                        department + ", " +
                        courseType + ", " +
                        isHonor + ", " +
                        prerequisitesStr
                );
                bw.newLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            TestCaseGenerator.writeCourses("course.txt", 15);
            TestCaseGenerator.writeStudents("student.txt", 40);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
