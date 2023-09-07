package com.example.couser.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Getter @Setter
	private String name;

	@Email
	@Getter @Setter
	private String email;

	@NotBlank
	@Getter @Setter
	private String phone;

	@NotBlank

	@Length(min = 8)
	@Getter @Setter
	private String password;

	@Getter
	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Order> orders = new ArrayList<>();

	public User(Long id, @NotBlank String name, @Email String email, @NotBlank String phone,
			@NotBlank @Length(min = 8) String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
}
