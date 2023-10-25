package com.nighthawk.hacks.classDataStruct;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


/*
Adapted from Person POJO, Plain Old Java Object.
 */
public class Person extends Generics{
    // Class data
    private static String classType = "Person";
    public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) {Person.key = key;}
	public enum KeyType implements KeyTypes {title, uid, name}

    // Instance data
    private String uid;  // user / person id
    private String password;
    private String name;
    

    // Constructor with zero arguments
    public Person() {
        super.setType(classType);
    }

    // Constructor used when building object from an API
    public Person(String uid, String password, String name) {
        this();  // runs zero argument constructor
        this.uid = uid;
        this.password = password;
        this.name = name;
    }

    /* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Person.key; }

    public String getUserID() {
        return uid;
    }

    /* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
    @Override
	public String toString() {		
		String output="";
		if (KeyType.uid.equals(this.getKey())) {
			output += this.uid;
		} else if (KeyType.name.equals(this.getKey())) {
			output += this.name;
		} else {
			output = super.getType() + ": " + this.uid + ", " + this.name;
		}
		return output;
	}

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Initialize static test data 
    public static Person[] init() {

        // basics of class construction
        Person p1 = new Person();
        p1.setName("Orlando Carcamo");
        p1.setUid("landoc@gmail.com");
        p1.setPassword("123Toby!");
            // no actions as dob default is good enough

        Person p2 = new Person();
        p2.setName("Aniket Chakradeo");
        p2.setUid("AniCriKet@gmail.com");
        p2.setPassword("123LexB!");


        Person p3 = new Person();
        p3.setName("Koham Somat");
        p3.setUid("sohamk@gmail.com");
        p3.setPassword("123Niko!");

        // Array definition and data initialization
        Person persons[] = {p1, p2, p3};
        return(persons);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Person persons[] = init();
        Person.setOrder(Person.KeyType.title);

        // iterate using "enhanced for loop"
        for( Person person : persons ) {
            System.out.println(person);  // print object
        }
    }

}