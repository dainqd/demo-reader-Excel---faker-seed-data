package com.example.testreadexcel.entities;

import com.example.testreadexcel.entities.basic.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Students extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private boolean gender;
    private String address;
    private String classRoom;
    private String rank;

}
