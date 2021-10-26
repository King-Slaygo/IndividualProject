package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE studentinformation (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "studentinformatio")
public class StudentList {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "first_name")
   private String firstName;
   
   @Column(name = "last_name")
   private String lastName;
   
   @Column(name = "age")
   private String age;

   @Column(name = "email")
   private String email;

   
public StudentList() {
	
}


public StudentList(Integer id, String firstName, String lastName, String age, String email) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.email = email;
}


public StudentList(String firstName, String lastName, String age, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.email = email;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getAge() {
	return age;
}


public void setAge(String age) {
	this.age = age;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}

public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}

@Override
public String toString() {
	return "MyEmployeeAldarei [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
			+ ", email=" + email + "]";
}



   
}