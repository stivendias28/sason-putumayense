package backend.sasonptumayense.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import backend.sasonptumayense.permission.Roles;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "identificationNumber"})})
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	@SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence_name", allocationSize = 1)
	private Integer id;
	
	@Basic
	
	@Column(nullable = false)
	private String name;

	@Column()
	private String secondName;
	
	@Column(nullable = false)
	private String lastName;

	@Column()
	private String secondLastName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String identificationNumber;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String photo;
	
	
	@Enumerated(EnumType.STRING)
	private Roles role;

	//RELATIONS 
	@ManyToOne()
	@JoinColumn(name = "identificationTypeId")
	private IdentificationType identificationType;
	
	@ManyToOne()
	@JoinColumn(name = "genderId")
	private Gender gender;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority((role.name())));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
