package com.ecommerce.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/* response */
public class CategoryDTO {
	private Long categoryId;
	private String categoryName;
}

