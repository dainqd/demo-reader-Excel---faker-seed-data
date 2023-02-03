package com.example.testreadexcel;

import com.example.testreadexcel.entities.Students;
import com.example.testreadexcel.entities.seeder.StudentSeeder;
import com.example.testreadexcel.repositories.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication
public class TestReadExcelApplication {
    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestReadExcelApplication.class, args);
    }

    //    Random students
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            long max = 10;
            Faker faker = new Faker(new Locale("en-US"));
            HashMap<Long, Students> mapStudents = new HashMap<>();
            String phone = "+843";
            String room = "BC";
            for (long i = 0; i < max; i++) {
                Students students = new Students(faker.number().randomNumber(),
                        faker.avatar().image(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        // Hash code: faker.numerify("8686868686"),
                        phone + String.valueOf(faker.number().numberBetween(10000000, 99999999)),
                        faker.date().birthday(),
                        faker.random().nextBoolean(),
                        faker.address().fullAddress(),
                        room + String.valueOf(faker.number().numberBetween(1000, 9999)),
                        String.valueOf(faker.number().numberBetween(1, 5)));
                students.setCreatedAt(LocalDateTime.now());
                students.setCreatedBy(0L);
                mapStudents.put(i, students);
//                studentRepository.save(students);
            }

            StudentSeeder studentSeeder = new StudentSeeder();
            studentSeeder.createStudents(studentRepository);
        };
    }
}
