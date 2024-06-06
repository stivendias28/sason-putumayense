package backend.sasonptumayense.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	String name;
	String secondName;
	String lastName;
	String secondLastName;
	String identificationTypeId;
	String identificationNumber;
	String email;
	String phone;
	String genderId;
	String username;
	String password;
	MultipartFile photo;

	String firstQuestion;
	String firstAnswer;
	String secondQuestion;
	String secondAnswer;
}
