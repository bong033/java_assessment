package java_assessment.service;


import java_assessment.model.Course;
import java_assessment.model.Student;

import java.util.*;

public class StudentService {

    int grades = 0;

    Map<String, Map<String, Integer>> studentGrade = new HashMap<>();
//    Map<String, Integer> courseGrade = new HashMap<>();

    private final Map<String, Student> students = new HashMap<>();

    public StudentService() {
        subscribeStudent(new Student("Adrian", "Tan", "adriantan@gmail.com", new Date(101, 0, 17), "stu01"));   // 2001 Jan 17
        subscribeStudent(new Student("Goh", "Hui Xin", "gohhuixin@hotmail.com", new Date(102, 8, 4), "stu02")); // 2002 Sep 4
        subscribeStudent(new Student("Alex", "Lim", "alexlim@gmail.com", new Date(103, 4, 30), "stu03"));       // 2003 May 30
    }

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        return students.containsKey(studentId);
    }

    public void showSummary() {
        //TODO implement
        //TODO: 1. display a title for this feature
        //TODO: 2. List each student AND the course(s) the student is enrolled into approvedCourses
        System.out.println("Students and the courses enrolled into");
        for (String key : students.keySet()) {
            Student student = students.get(key);
            student.printFullName();

            List<Course> courses = student.getApprovedCourses();

            if (courses == null) {
                System.out.println("\t\tNo Enrolled Courses");
            } else {
                for (Course course : courses) {
                    System.out.println("\t\t" + course);
                }
            }
        }
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public void insertDisplayPassedGrade(String studentId, String courseId) {
        //TODO implement
        //TODO: 1. display no enrolled if student did not enroll to the course
        //TODO: 2. insert student grade and display only those who have passing grade above 50

        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            List<Course> courses = student.getApprovedCourses();
            student.printFullName();

            if (courses == null) {
                System.out.println("\t\tNo Enrolled Courses");
            } else {

                System.out.println("Insert grade");
                Scanner scanner = new Scanner(System.in);
                int grade = scanner.nextInt();
                grades += grade;
                students.get(studentId).setGrade(grade);

                Map<String, Integer> courseGrade = studentGrade.get(studentId);

                if (courseGrade == null) {
                    courseGrade = new HashMap<>();
                    studentGrade.put(studentId, courseGrade);
                }
                courseGrade.put(courseId, grade);

                studentGrade.forEach((outerK, outerV) -> {
                    outerV.forEach((k, v) -> {
                        if (v >= 50) {
                            System.out.println("Student ID: " + outerK + " " + k + " pass with grade: " + v);
                        }
                    });
                });
            }
        }
    }
}