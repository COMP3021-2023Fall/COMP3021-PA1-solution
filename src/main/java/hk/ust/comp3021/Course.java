package hk.ust.comp3021;

import java.util.*;

public abstract class Course implements Enrollable {
    public static final int INITIAL_CAPACITY = 10;

    protected String courseCode;
    protected String department;
    protected int capacity;
    protected List<String> enrolledStudents;
    protected List<String> waitlist;

    public Course(String courseCode, String department) {
        this.courseCode = courseCode;
        this.department = department;
        this.capacity = INITIAL_CAPACITY;
        this.enrolledStudents = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    /**
     * TODO: Task 2
     *
     * @param student the student being enrolled to the course
     */
    public void enroll(Student student) {
        enrolledStudents.add(student.getStudentID());
    }

    /**
     * TODO: Task 5
     *
     * @param student the student being checked
     * @return true if the student fulfills enrollment criteria
     */
    public abstract boolean enrollmentCriteria(Student student);

    public void guaranteeCapacity() {
        if (this.enrolledStudents.size() > INITIAL_CAPACITY) {
            this.capacity = this.enrolledStudents.size();
        }
    }

    public String getDepartment() {
        return department;
    }

    public boolean isStudentEnrolled(Student student) {
        return this.enrolledStudents.contains(student.getStudentID());
    }

    public void addWaitlist(Student student) {
        this.waitlist.add(student.getStudentID());
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<String> getWaitlist() {
        return waitlist;
    }
}

