package com.poly.asm.service;

import java.util.List;

import com.poly.asm.dto.response.product.ProductResponse;

public interface FavoriteService {
	List<ProductResponse> getFavoriteUser(int userId);
	String toggleLike(int userId, int productId);
}
