package hk.ust.comp3021;

public class CommonCoreCourse extends Course implements Enrollable {
    private boolean isHonorsCourse;

    public CommonCoreCourse(String courseCode, String department, boolean isHonorsCourse) {
        super(courseCode, department);
        this.isHonorsCourse = isHonorsCourse;
    }

    @Override
    public boolean enrollmentCriteria(Student student) {
        return !isHonorsCourse // if the course is not an honors course, criteria is fulfilled
                || student.getCGA() >= 3.5; // Otherwise CGA requirement needs to be fulfilled
    }

    @Override
    public void enrollWithCondition(Student student) throws CourseFullException {
        if (enrolledStudents.size() < capacity) {
            if (enrollmentCriteria(student)) {
                enrolledStudents.add(student.getStudentID());
            }
        } else {
            throw new CourseFullException("Course is full!");
        }
    }
}
