package com.nighthawk.spring_portfolio.mvc.person;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Convert;
import static jakarta.persistence.FetchType.EAGER;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
>>>>>>> 3ac412c8af551f788e42e003c04d2b3bfbabc37b

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Convert(attributeName ="person", converter = JsonType.class)
public class Person {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    // To be implemented
    @ManyToMany(fetch = EAGER)
    private Collection<PersonRole> roles = new ArrayList<>();

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Initialize static test data 
    public static Person[] init() {

        // basics of class construction
        Person p1 = new Person();
<<<<<<< HEAD
        p1.setName("Orlando Carcamo");
        p1.setEmail("orlando@gmail.com");
        p1.setPassword("123Orlando!");
        // adding Note to notes collection
        try {  // All data that converts formats could fail
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("12-08-2006");
            p1.setDob(d);
        } catch (Exception e) {
            // no actions as dob default is good enough
        }

        Person p2 = new Person();
        p2.setName("Soham Kamat");
        p2.setEmail("soham@gmail.com");
        p2.setPassword("123Soham!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-02-2006");
            p2.setDob(d);
        } catch (Exception e) {
        }

        Person p3 = new Person();
        p3.setName("Aniket Chakradeo");
        p3.setEmail("aniket@gmail.com");
        p3.setPassword("123Aniket!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-03-2006");
            p3.setDob(d);
        } catch (Exception e) {
        }

        Person p4 = new Person();
        p4.setName("Kevin Du");
        p4.setEmail("kevin@gmail.com");
        p4.setPassword("123Kevin!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-04-2006");
            p4.setDob(d);
        } catch (Exception e) {
        }
=======
        p1.setName("Theo Huntalas");
        p1.setEmail("theo.h131@gmail.com");
        p1.setPassword("THEOLOVESCODE");

        Person p2 = new Person();
        p2.setName("Kaiden Do");
        p2.setEmail("lexb@gmail.com");
        p2.setPassword("123LexB!");

        Person p3 = new Person();
        p3.setName("Rachit Jaiswal");
        p3.setEmail("rjaiswal.sd.77@gmail.com");
        p3.setPassword("Iamhungry12#!");

        Person p4 = new Person();
        p4.setName("Grace Wang");
        p4.setEmail("grace@gmail.com");
        p4.setPassword("123Grace!");
>>>>>>> 3ac412c8af551f788e42e003c04d2b3bfbabc37b

        Person p5 = new Person();
        p5.setName("John Mortensen");
        p5.setEmail("jm1021@gmail.com");
        p5.setPassword("123Qwerty!");
<<<<<<< HEAD
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("10-21-1959");
            p5.setDob(d);
        } catch (Exception e) {
        }
=======
>>>>>>> 3ac412c8af551f788e42e003c04d2b3bfbabc37b

        // Array definition and data initialization
        Person persons[] = {p1, p2, p3, p4, p5};
        return(persons);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Person persons[] = init();

        // iterate using "enhanced for loop"
        for( Person person : persons) {
            System.out.println(person);  // print object
        }
    }

}