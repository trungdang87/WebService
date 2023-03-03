package com.example.demo.datacreation;

import com.example.demo.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StudentDataTest {
    @InjectMocks
    StudentData studentData;

    @Test
    public void testGetAllStudents() {
        studentData.initializeStudentData();
        List<Student> students = studentData.getAllStudents();
        Assert.assertNotNull(students);
        Assert.assertTrue(students.size()==3);
    }

    @Test
    public void testGetStudentById() {
        studentData.initializeStudentData();
        Student student = studentData.getStudentById("student2");
        Assert.assertNotNull(student);
        Assert.assertTrue("student2FirstName".equals(student.getFirstName()));
    }

    @Test
    public void testGetStudentById_invalid_studentId() {
        studentData.initializeStudentData();
        Student student = studentData.getStudentById("invalidId");
        Assert.assertNull(student);
    }

}
