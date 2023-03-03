package com.example.demo.datacreation;

import com.example.demo.model.Student;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentData {

    private Map<String, Student> studentMap = new HashMap<>();

    public List<Student> getAllStudents() {
        if (!CollectionUtils.isEmpty(studentMap)) {
            return new ArrayList<Student>(studentMap.values());
        }
        return new ArrayList<>();
    }

    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeStudentData() {
        try {
            List<Student> students = new CsvToBeanBuilder(new FileReader("/Users/trungdang/project/demo/src/main/resources/data/student.csv"))
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

}
