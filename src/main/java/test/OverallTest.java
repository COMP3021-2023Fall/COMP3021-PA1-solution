package test;

import hk.ust.comp3021.EnrollmentSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OverallTest {

    public static void compareDocuments(String fileNameA, String fileNameB){
        try {
            BufferedReader brA = new BufferedReader(new FileReader(fileNameA));
            BufferedReader brB = new BufferedReader(new FileReader(fileNameB));
            String lineA;
            int linenum = 0;
            while ((lineA = brA.readLine()) != null) {
                linenum++;
                String lineB = brB.readLine();
                if (!lineA.equals(lineB)) {
                    String errorMessage = String.format("Line %d has: \n %s \n %s", linenum, lineA, lineB);
                    System.out.println(errorMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            EnrollmentSystem system = new EnrollmentSystem();
            system.parseStudents("student.txt");
            system.parseCourses("course.txt");
            system.enrollFirstRound();
            system.writeCourseEnrollment("firstRoundEnrollmentTest.txt");
            system.enrollSecondRound();
            system.writeCourseEnrollment("secondRoundEnrollmentTest.txt");
            system.writeCourseAnalysis("dataAnalyticsTest.txt");
            OverallTest.compareDocuments("firstRoundEnrollment.txt", "firstRoundEnrollmentTest.txt");
            OverallTest.compareDocuments("secondRoundEnrollment.txt", "secondRoundEnrollmentTest.txt");
            OverallTest.compareDocuments("dataAnalytics.txt", "dataAnalyticsTest.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
