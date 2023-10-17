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
            for (int i = 1; i < 5; ++i) {
                EnrollmentSystem system = new EnrollmentSystem();
                system.parseStudents(String.format("student%d.txt", i));
                system.parseCourses(String.format("course%d.txt", i));
                system.enrollFirstRound();
                system.writeCourseEnrollment(String.format("firstRoundEnrollmentTest%d.txt", i));
                system.enrollSecondRound();
                system.writeCourseEnrollment(String.format("secondRoundEnrollmentTest%d.txt", i));
                system.writeCourseAnalysis(String.format("dataAnalyticsTest%d.txt", i));
                OverallTest.compareDocuments(String.format("firstRoundEnrollment%d.txt", i), String.format("firstRoundEnrollmentTest%d.txt", i));
                OverallTest.compareDocuments(String.format("secondRoundEnrollment%d.txt", i), String.format("secondRoundEnrollmentTest%d.txt", i));
                OverallTest.compareDocuments(String.format("dataAnalytics%d.txt", i), String.format("dataAnalyticsTest%d.txt", i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
