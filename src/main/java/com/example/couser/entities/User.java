package com.example.couser.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
}
