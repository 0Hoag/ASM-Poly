package com.poly.asm.dto.request.address;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
	int id;
	
	@NotEmpty(message = "Address cannot be empty")
	String address;
	
	@NotEmpty(message = "Phone number cannot be empty")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
	String phoneNumber;
	
	@NotEmpty(message = "User name cannot be empty")
	String userName;
	int userId;
}
