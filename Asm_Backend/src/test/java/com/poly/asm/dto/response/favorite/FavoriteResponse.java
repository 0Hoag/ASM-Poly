package com.poly.asm.dto.response.favorite;



import com.poly.asm.dto.response.product.ProductResponse;

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
public class FavoriteResponse {
	 int id;
	 String likedAt;
	 ProductResponse product;

}
