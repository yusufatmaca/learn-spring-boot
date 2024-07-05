package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends  JpaRepository<Category, Long> {
	Category findByCategoryName(String categoryName); // JPA understands and implements this function. Totally magic! :-)

}
