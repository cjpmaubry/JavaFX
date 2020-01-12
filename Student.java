package application;

import java.time.LocalDate;

public class Student 
{
	int Id;
	String Name;
	String Gender;
	LocalDate BirthDate;
	String Photo;
	Float Mark;
	public Float getMark() {
		return Mark;
	}
	public void setMark(Float mark) {
		Mark = mark;
	}


	String Comments;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public LocalDate getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public Student(int id, String name, String gender, LocalDate birthDate, String photo,Float mark, String comments) {
		super();
		Id = id;
		Name = name;
		Gender = gender;
		BirthDate = birthDate;
		Photo = photo;
		Comments = comments;
	}
		
	public Student() {
		super();
		
	}
	
	
	public Student(String name, String gender)
	{
		Name=name;
		Gender=gender;
	}
}
