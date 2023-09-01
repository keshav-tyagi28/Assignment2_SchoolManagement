package com.osttra.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

	private String username;
	private String password;
	private String completeName;
	private String email;
	private String role;
	private String status="inactive";
	private Integer counter=0;
	public User(String username, String password, String completeName, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.completeName = completeName;
		this.email = email;
		this.role = role;
	}

}
