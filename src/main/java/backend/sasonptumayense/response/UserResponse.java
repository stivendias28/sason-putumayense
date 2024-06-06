package backend.sasonptumayense.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	Integer id;
    String name;
	String secondName;
	String lastName;
	String secondLastName;
	String identificationType;
	String identificationNumber;
	String email;
	String rol;
	String phone;
	String gender;
	String photo;
}
