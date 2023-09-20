package hk.ust.comp3021;

import java.io.*;
import java.util.*;

public class EnrollmentSystem {
    public List<Student> students;
    public Map<String, Course> courses;

    public EnrollmentSystem() {
        this.students = new ArrayList<>();
        this.courses = new HashMap<>();
    }

    /**
     * TODO: Part 1
     * The first two choices in students' preferences must be enrolled,
     * regardless of course capacity or prerequisites.
     * The course capacity, initially sets at 10, expands if necessary
     * to accommodate these guaranteed choices.
     */
    public void enrollFirstRound() {
        for (Student student : students) {
            if (student.getTopPreferences(2) != null) {
                for (String courseCode : student.getTopPreferences(2)) {
                    courses.get(courseCode).enroll(student);
                }
            }
        }

        for (Course course : courses.values()) {
            course.guaranteeCapacity();
        }
    }

    /**
     * TODO: Part 2
     * Task 3: All third choices from preferences will be processed first,
     * followed by fourth choices, and so on until the last preference.
     * Task 4: MajorCourses and CommonCoreCourse should implement Enrollable.
     * They also have different priorities: common core courses gives priority
     * to descending seniority (year of study), while major courses grants
     * precedence to students from the same department. The courses will be
     * filled, first by the priority, then in the descending order of CGA,
     * and finally in the descending order of StudentID.
     */
    public void enrollSecondRound() {
        for (int i = 3; i <= 10; i++) {
            List<Student> prioritizedStudents = new ArrayList<>();
            List<Student> nonPrioritizedStudents = new ArrayList<>();
            for (Student student : students) {
                String preference = student.getPreference(i);
                if (preference != null) {
                    Course course = courses.get(preference);
                    if (course instanceof MajorCourse && // the course is a major course
                            Objects.equals(course.getDepartment(), student.getDepartment())) { // both belong to the same department
                        prioritizedStudents.add(student);
                    } else {
                        nonPrioritizedStudents.add(student);
                    }
                }
            }

            // Handle the prioritized students first, then the non-prioritized
            for (List<Student> list : List.of(prioritizedStudents, nonPrioritizedStudents)) {
                for (Student student : list) { // enroll the students in the list sequentially
                    String preference = student.getPreference(i); // null preference was checked in the last iteration
                    Course course = courses.get(preference);
                    try {
                        course.enrollWithCondition(student); // this method throws CourseFullException
                    } catch (CourseFullException e) {
                        course.addWaitlist(student); // add to waitlist if the course is full
                    }
                }
            }
        }
    }

    /**
     * TODO: Task 7
     * Find the number of Teaching Assistants (TAs) required, given that
     * 1 TA is needed for every 5 students in a course.
     *
     * @return the number of TAs required
     */
    public int findNumTA() {
        int numTA = 0;
        for (Course course : courses.values()) {
            int numStudents = course.enrolledStudents.size();
            numTA += (numStudents / 5) + (numStudents % 5 != 0 ? 1 : 0);
        }
        return numTA;
    }

    /**
     * TODO: Task 8
     * Find the number of students who successfully enrolled in
     * all their course preferences.
     *
     * @return the number of students
     */
    public int findNumAllSuccess() {
        int numAllSuccess = 0;
        for (Student student : students) {
            boolean success = student.getPreferences() == null || // students who have no preferences are success
                    student.getPreferences().stream().allMatch(
                            preference -> courses.get(preference).isStudentEnrolled(student) // all preferences are enrolled
                    );
            numAllSuccess += success ? 1 : 0;
        }
        return numAllSuccess;
    }

    /**
     * TODO: Task 9
     * Identify the students who have not enrolled in any
     * common core courses.
     *
     * @return the list of StudentID
     */
    public List<String> findListNoCommonCore() {
        List<String> listNoCommonCore = new ArrayList<>();
        for (Student student : students) {
            boolean hasNoCommonCore = student.getPreferences() == null || // students who have no preferences
                    student.getPreferences().stream().noneMatch( // all enrolled courses are not common core course
                            preference -> courses.get(preference).isStudentEnrolled(student) // the student enrolls in the course
                                    && courses.get(preference) instanceof CommonCoreCourse // and is a common core course
                    );
            if (hasNoCommonCore) {
                listNoCommonCore.add(student.getStudentID());
            }
        }
        return listNoCommonCore;
    }

    public void parseStudents(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // TODO: Task 1
                String[] parts = line.split(", ");
                String studentID = parts[0];
                String department = parts[1];
                int yearOfStudy = Integer.parseInt(parts[2]);
                double CGA = Double.parseDouble(parts[3]);
                List<String> preferences = Arrays.asList(parts[4].substring(1, parts[4].length() - 1).split(" "));
                List<String> completedCourses = Arrays.asList(parts[5].substring(1, parts[5].length() - 1).split(" "));
                preferences = Objects.equals(preferences.get(0), "") ? null : preferences;
                completedCourses = Objects.equals(completedCourses.get(0), "") ? null : completedCourses;

                Student student = new Student(studentID, department, yearOfStudy, CGA, preferences, completedCourses);
                students.add(student);
            }
        }
    }

    public void parseCourses(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // TODO: Task 1
                String[] parts = line.split(", ");
                String courseCode = parts[0];
                String department = parts[1];
                String courseType = parts[2];
                boolean isHonorsCourse = Boolean.parseBoolean(parts[3]);
                List<String> prerequisites = Arrays.asList(parts[4].substring(1, parts[4].length() - 1).split(" "));
                prerequisites = Objects.equals(prerequisites.get(0), "") ? null : prerequisites;

                if (courseType.equals("Major")) {
                    MajorCourse course = new MajorCourse(courseCode, department, prerequisites);
                    courses.put(courseCode, course);
                } else {
                    CommonCoreCourse course = new CommonCoreCourse(courseCode, department, isHonorsCourse);
                    courses.put(courseCode, course);
                }
            }
        }
    }

    public void writeCourseEnrollment(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Course course : courses.values()) {
                bw.write(course.courseCode + ", " + course.capacity + ", [" +
                        String.join(" ", course.enrolledStudents) + "], [" +
                        String.join(" ", course.waitlist) + "]"
                );
                bw.newLine();
            }
        }
    }

    public void writeCourseAnalysis(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(findNumTA() + ", " + findNumAllSuccess() + ", [" + String.join(" ", findListNoCommonCore()) + "]");
            bw.newLine();
        }
    }

    public static void main(String[] args) {
        EnrollmentSystem system = new EnrollmentSystem();

        try {
            system.parseStudents("student.txt");
            system.parseCourses("course.txt");
            system.enrollFirstRound();
            system.writeCourseEnrollment("firstRoundEnrollment.txt");
            system.enrollSecondRound();
            system.writeCourseEnrollment("secondRoundEnrollment.txt");
            system.writeCourseAnalysis("dataAnalytics.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
