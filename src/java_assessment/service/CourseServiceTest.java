package java_assessment.service;

import java_assessment.model.Course;
import java_assessment.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseService courseService;                // private instance
    private StudentService studentService;              // private instance

    @BeforeEach
    void setUp() {
        courseService = new CourseService();            // initialize courseService
        studentService= new StudentService();           // initialize studentService
    }

    @Test
    void testEnrollStudent() {
        Student student = studentService.findStudent("stu01");
        Student student2 = studentService.findStudent("stu02");
        courseService.enrollStudent("INTRO-CS-1", student);
        courseService.enrollStudent("INTRO-CS-1", student2);

        List<Student> enrolledStudents = courseService.enrolledStudents.get("INTRO-CS-1");
        assertNotNull(enrolledStudents, "List of enrolled students should not be null");
        assertEquals(2, enrolledStudents.size(),"Enrolled student size should be one");

    }

    @Test
    void testGetCourse() {
        Course course = courseService.getCourse("INTRO-CS-5");
        assertNotNull(course, "Course should be found");
        assertEquals("INTRO-CS-5", course.getId(), "Course Id should match");
        assertNotEquals("INTRO-CS-5", course.getId(), "Course Id should not match");
    }
}