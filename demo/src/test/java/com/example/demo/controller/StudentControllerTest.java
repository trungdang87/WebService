package com.example.demo.controller;

import com.example.demo.datacreation.StudentData;
import com.example.demo.model.Student;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private Map<String, Student> studentMap = new HashMap<>();

    @Mock
    StudentData studentData;

    @InjectMocks
    StudentController studentController;

    @Before
    public void init() {
        try {
            List<Student> students = new CsvToBeanBuilder(new FileReader("src/main/resources/data/student.csv"))
                    .withType(Student.class)
                    .build()
                    .parse();
            for (Student s : students) {
                studentMap.put(s.getStudentId(), s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllStudents() {
        Mockito.when(studentData.getAllStudents()).thenReturn(new ArrayList<Student>(studentMap.values()));
        List<Student> studentList = studentController.getAllStudents();
        Assert.assertNotNull(studentList);
        Assert.assertEquals(3, studentList.size());
    }

    @Test
    public void testGetStudentById() {
        Mockito.when(studentData.getStudentById("student1")).thenReturn(studentMap.get("student1"));
        Student student = studentController.getStudentById("student1");
        Assert.assertNotNull(student);
        Assert.assertTrue("student1FirstName".equals(student.getFirstName()));
    }

    @Test
    public void testGetStudentById_invalid_studentId() {
        Mockito.when(studentData.getStudentById("invalid")).thenReturn(studentMap.get("invalid"));
        Student student = studentController.getStudentById("invalid");
        Assert.assertNull(student);
    }
}
