package dmacc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue
	 private long id;
	 private String firstName;
	 private String lastName;
	 private int year;
	 
	 

}
