package com.example.demo.datacreation;

import com.example.demo.model.Student;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
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
            List<Student> students = new CsvToBeanBuilder(new FileReader("src/main/resources/data/student.csv"))
                    .withType(Student.class)
                    .build()
                    .parse();
            for (Student s : students) {
                String encryptedYear = "";
                String decryptedYear = "";
                try {
                    // Generating objects of KeyGenerator &
                    // SecretKey
                    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                    SecretKey myDesKey = keygenerator.generateKey();

                    // Creating object of Cipher
                    Cipher desCipher = Cipher.getInstance("DES");

                    // Creating byte array to store string
                    byte[] studentYear
                            = String.valueOf(s.getYear()).getBytes("UTF8");

                    // Encrypting text
                    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                    byte[] textEncrypted = desCipher.doFinal(studentYear);

                    // Converting encrypted byte array to string
                    encryptedYear = new String(textEncrypted);
                    System.out.println("encrypted student year " + encryptedYear);

                    // Decrypting text
                    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                    byte[] textDecrypted
                            = desCipher.doFinal(textEncrypted);

                    // Converting decrypted byte array to string
                    decryptedYear = new String(textDecrypted);
                    System.out.println("decrypted student year " + decryptedYear);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                s.setDecryptedYear(decryptedYear);
                s.setEncryptedYear(encryptedYear);
                studentMap.put(s.getStudentId(), s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void checkIfFileGetUpdated() {
        File studentFile = new File("src/main/resources/data/student.csv");
        File updatedStudentFile = new File("src/main/resources/data/updatedStudentFile.csv");
        int studentFileHash = studentFile.hashCode();
        int updatedStudentFileHash = updatedStudentFile.hashCode();
        System.out.println("studentFileHash " + studentFileHash);
        System.out.println("updatedStudentFileHash " + updatedStudentFileHash);
    }

}
