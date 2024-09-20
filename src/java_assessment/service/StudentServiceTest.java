package java_assessment.service;

import java_assessment.model.Course;
import java_assessment.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;          // private attribute

    @BeforeEach
    void setUp() {
        studentService = new StudentService();      // create a new instance of studentService before the test is run
    }

    @Test
    void testFindStudent() {
        Student student = studentService.findStudent("stu01");                                                                  //find the student stu01 created by studentService
        assertNotNull(student, "Student should be found");                                                                      // check whether the student exists through the assertions to determine we have found the student
        assertEquals("Adrian", student.getFirstName(), "FirstName of the student should be Adrian");                    // firstname should be "Adrian"
        assertEquals("Tan", student.getLastName(), "LastName of the student should be Tan");                            // firstname should be "Tan"
        assertEquals("adriantan@gmail.com", student.getEmail(), "email of the student should be adriantan@gmail.com");  // firstname should be "adriantan@gmail.com"
    }

    @Test
    void testNotFoundStudent() {
        Student student = studentService.findStudent("stu07");
        assertNull(student, "Student should NOT be found");
    }

    @Test
    void testIsSubscribed() {
        assertTrue(studentService.isSubscribed("stu01"));   // test that the student exists
        assertFalse(studentService.isSubscribed("stu00"));  // test that the student does NOT exist
    }

    @Test
    void testIsAttendingCourse() {
        Course courseJava = new Course("Introduction to Computer Science", "INTRO-CS-J", 100);
        Student student = new Student("Tony", "Stark", "tonystark@gmail.com", new Date(101,0,17), "stu0X");
        student.enrollToCourse(courseJava);
        assertTrue(student.isAttendingCourse("INTRO-CS-J"), "Student should be attending course");
        assertFalse(student.isAttendingCourse("INTRO-CS-X"), "Student should not be attending this course");

    }
}