package com.example.testreadexcel.entities.seeder;

import com.example.testreadexcel.entities.Students;
import com.example.testreadexcel.enums.SheetIndex;
import com.example.testreadexcel.repositories.StudentRepository;
import com.example.testreadexcel.utils.Constants;
import com.example.testreadexcel.utils.ExcelUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentSeeder {
    ExcelUtils excelUtils = new ExcelUtils();

    public void createStudents(StudentRepository studentRepository) {
        try {
            excelUtils.setExcelFile(Constants.SEED_URI, SheetIndex.STUDENT.getValue());
            int count = excelUtils.getSheetRowSize(Constants.SEED_URI, SheetIndex.STUDENT.getValue());
            System.out.println("ddd " + count);
            Students students = new Students();
            for (int i = 1; i < count; i++) {
                if (excelUtils.getCellData("stt", i) == null) {
                    break;
                }
                String address = excelUtils.getCellData("address", i);
                if (address != null) {
                    students.setAddress(address);
                }
                String birthday = excelUtils.getCellData("birthday", i);
                if (birthday != null) {
                    Date birthdayConvert = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                    students.setBirthday(birthdayConvert);
                }
                String email = excelUtils.getCellData("email", i);
                if (email != null) {
                    students.setEmail(email);
                }
                String first_name = excelUtils.getCellData("first_name", i);
                if (first_name != null) {
                    students.setFirstName(first_name);
                }
                String gender = excelUtils.getCellData("gender", i);
                if (gender != null) {
                    students.setGender(Boolean.parseBoolean(gender));
                }
                String last_name = excelUtils.getCellData("last_name", i);
                if (last_name != null) {
                    students.setLastName(last_name);
                }
                String phone_number = excelUtils.getCellData("phone_number", i);
                if (phone_number != null) {
                    students.setPhoneNumber(phone_number);
                }
                String rank = excelUtils.getCellData("rank", i);
                if (rank != null) {
                    students.setRank(rank);
                }
                String class_room = excelUtils.getCellData("class_room", i);
                if (class_room != null) {
                    students.setClassRoom(class_room);
                }
                String avatar = excelUtils.getCellData("avatar", i);
                if (avatar != null) {
                    students.setAvatar(avatar);
                }
                students.setCreatedAt(LocalDateTime.now());
                studentRepository.save(students);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
