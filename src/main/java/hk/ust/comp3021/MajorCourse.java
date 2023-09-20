package hk.ust.comp3021;

import java.util.*;

public class MajorCourse extends Course implements Enrollable {
    List<String> prerequisites;

    public MajorCourse(String courseCode, String department, List<String> prerequisites) {
        super(courseCode, department);
        this.prerequisites = prerequisites;
    }

    @Override
    public boolean enrollmentCriteria(Student student) {
        return prerequisites == null || // if the course has no prerequisites, criteria must be fulfilled
                prerequisites.stream().allMatch( // if all prerequisites are completed before
                        prerequisite ->
                                student.getCompletedCourses() != null && // if student has no completed courses, early out
                                        student.getCompletedCourses().contains(prerequisite) // prerequisite is completed
                );
    }

    @Override
    public void enrollWithCondition(Student student) throws CourseFullException {
        if (enrollmentCriteria(student)) {
            if (enrolledStudents.size() == capacity) {
                throw new CourseFullException("Course is full!");
            }
            enrolledStudents.add(student.getStudentID());
        }
    }
}