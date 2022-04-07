package com.college.entity;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String Name;
	public int age;
	public String address;
	public long phoneNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, int age, String address, long phoneNumber) {
		super();
		this.id = id;
		Name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", Name=" + Name + ", age=" + age + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}
	


	
}