package com.famgomjas.ws.requestBody;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.famgomjas.ws.util.Constants;
import com.famgomjas.ws.validator.EnumList;

public class RegisterRequest {
	@NotBlank(message = "El usuario no puede estar vacio")
	@Size(min = 4, max = 50, message = "El usuario tiene que tener minimo 4 caracteres")
	private String user;

	@NotBlank(message = "La contraseña no puede estar vacia")
	@Size(min = 8, max = 50, message = "La contraseña tiene que tener minimo 8 caracteres")
	private String password;
	
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(min = 2, max = 40, message = "El nombre tiene que tener minimo 2 caracteres")
	private String name;
	
	@NotBlank(message = "El apellido no puede estar vacio")
	@Size(min = 2, max = 40, message = "El apellido tiene que tener minimo 2 caracteres")
	private String lastName;
	
	private String lastNameMother;
	
	@NotNull(message = "La fecha de nacimiento no puede estar vacio")
	@Past(message = "La fecha de nacimeinto no es valida")
	private Date birthdate;
	
	@EnumList(targetClassType=Constants.GENDERS.class, message="El genero no es valido")
	private String gender;
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameMother() {
		return lastNameMother;
	}

	public void setLastNameMother(String lastNameMother) {
		this.lastNameMother = lastNameMother;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
