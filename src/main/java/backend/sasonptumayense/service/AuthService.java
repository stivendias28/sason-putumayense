package backend.sasonptumayense.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.sasonptumayense.Controllers.FileController;
import backend.sasonptumayense.jwt.JwtService;
import backend.sasonptumayense.model.Gender;
import backend.sasonptumayense.model.IdentificationType;
import backend.sasonptumayense.model.User;
import backend.sasonptumayense.permission.Roles;
import backend.sasonptumayense.repository.UserRepository;
import backend.sasonptumayense.request.LoginRequest;
import backend.sasonptumayense.request.RegisterRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.AuthResponse;
import backend.sasonptumayense.response.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final GenderService genderService;
	private final identificationTypeService identificationTypeService;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final FileController fileController;

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		User userData = (User) user;
		System.out.println("User: " + user);
		String token = jwtService.getToken(user, userData.getRole().toString());
		return createAuthResponse(userData, token);
	}
	
	public AuthResponse register(RegisterRequest request, MultipartFile photo) {
		IdentificationType identificationType = identificationTypeService.getIdentificationTypeById(Integer.parseInt(request.getIdentificationTypeId()));
		Gender gender = genderService.getGenderById(Integer.parseInt(request.getGenderId()));
		String urlImage = "users/user-default.jpg";

		if(identificationType == null || gender == null) {
			return null;
		}

		if(request.getPhoto() != null) {
			ResponseEntity<ApiResponse> responseUploadFile = fileController.uploadFile(request.getPhoto(), request.getIdentificationNumber(), "users");
		
			if(responseUploadFile.getStatusCode() != HttpStatus.OK) {
				return null;
			}

			urlImage = responseUploadFile.getBody().getData().toString();
		}


		User user = User.builder()
				.name(request.getName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.identificationType(identificationType)
				.gender(gender)
				.identificationNumber(request.getIdentificationNumber())
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Roles.USER)
				.photo(urlImage)
				.build();
				
		User saveAccount = userRepository.save(user);
		return createAuthResponse(saveAccount, jwtService.getToken(user, saveAccount.getRole().toString()));
		//return createAuthResponse(user, null);
	}





	//RESPUESTA PARA EL CLIENTE
	private AuthResponse createAuthResponse(User user, String token) {
		UserResponse userResponse = UserResponse.builder()
				.id(user.getId())
				.name(user.getName())
				.secondName(user.getSecondName())
				.lastName(user.getLastName())
				.secondLastName(user.getSecondLastName())
				.identificationType(user.getIdentificationType().getNameIdentificationType())
				.identificationNumber(user.getIdentificationNumber())
				.email(user.getEmail())
				.phone(user.getPhone())
				.rol(user.getRole().toString())
				.gender(user.getGender().getNameGender())
				.photo(user.getPhoto())
				.build();

		return AuthResponse.builder()
				.user(userResponse)
				.token(token)
				.build();
	}
}
