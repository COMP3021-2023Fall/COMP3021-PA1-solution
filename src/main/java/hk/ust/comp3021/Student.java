package hk.ust.comp3021;

import java.util.*;

public class Student {
    private String StudentID;
    private String Department;
    private int YearOfStudy;
    private double CGA;
    private List<String> Preferences;
    private List<String> CompletedCourses;

    public Student(String studentID, String department, int yearOfStudy, double CGA, List<String> preferences, List<String> completedCourses) {
        this.StudentID = studentID;
        this.Department = department;
        this.YearOfStudy = yearOfStudy;
        this.CGA = CGA;
        this.Preferences = preferences;
        this.CompletedCourses = completedCourses;
    }

    public Student() {

    }

    public String getPreference(int index) {
        return Preferences == null || (index > Preferences.size()) ? null: Preferences.get(index - 1);
    }

    public List<String> getTopPreferences(int n) {
        return Preferences == null ? null: Preferences.subList(0, Math.min(n, Preferences.size()));
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getDepartment() {
        return Department;
    }

    public int getYearOfStudy() {
        return YearOfStudy;
    }

    public double getCGA() {
        return CGA;
    }

    public List<String> getPreferences() {
        return Preferences;
    }

    public List<String> getCompletedCourses() {
        return CompletedCourses;
    }
}