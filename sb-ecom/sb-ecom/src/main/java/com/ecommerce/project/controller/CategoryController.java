package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController // create RESTful web service
@RequestMapping("/api") // all URLs begin with "/api/"
public class CategoryController {
	@Autowired // automatic constructor injection
	private CategoryService categoryService;


	@GetMapping("/public/categories") // handle requests coming from "/api/public/categories" address
	public ResponseEntity<List<Category>> /* `ResponseEntity`  */ getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories /* body */, HttpStatus.OK /* status */);
	}

	@PostMapping("/public/categories")
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/admin/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
			String status = categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(status /* body */, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}

	@PutMapping("/public/categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@RequestBody Category category,
																							 	@PathVariable Long categoryId) {
		try {
			Category savedCategory = categoryService.updateCategory(category, categoryId);
			return new ResponseEntity<>("Category with CategoryID: " + categoryId + " saved.",
					HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
}
