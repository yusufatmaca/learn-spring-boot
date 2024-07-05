package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // create RESTful web service
@RequestMapping("/api") // all URLs begin with "/api/"
public class CategoryController {
	@Autowired // automatic constructor injection
	private CategoryService categoryService;

	/* EXAMPLE FOR `@RequestParam`
	@GetMapping("/echo")
	public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello World!") String message) {
		return new ResponseEntity<>("Echoed Message: " + message, HttpStatus.OK);
	}
	*/

	@GetMapping("/public/categories") // handle requests coming from "/api/public/categories" address
	public ResponseEntity<CategoryResponse> getAllCategories(
			@RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name="sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
			@RequestParam(name="sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<>(categoryResponse /* body */, HttpStatus.OK /* status */);
	}

	@PostMapping("/public/categories")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO cDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(cDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("/admin/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
		CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(deletedCategory /* body */, HttpStatus.OK);
	}

	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
																							 	@PathVariable Long categoryId) {
			return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, categoryId),
					HttpStatus.OK);
	}
}
