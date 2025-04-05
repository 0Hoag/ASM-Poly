package com.example.ASM.dto.response.user;


import com.example.ASM.dto.response.product.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteResponse {
	 int id;
	 String likedAt;
	 String product;
}
