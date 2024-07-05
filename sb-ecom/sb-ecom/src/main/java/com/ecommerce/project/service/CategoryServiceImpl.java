package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
	// private List<Category> categories = new ArrayList<>();
	// private long nextId = 1L; it must be generated in DB level

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending(); // ternary operation occurs

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

		List<Category> categories = categoryPage.getContent();

		if (categories.isEmpty())
			throw new APIException("There is no category!");
		List<CategoryDTO> categoryDTOS = categories.stream()
				.map(category -> modelMapper.map(category, CategoryDTO.class))
				.toList();

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoryDTOS);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPages(categoryPage.getTotalPages());
		categoryResponse.setLastPage(categoryPage.isLast());
		return categoryResponse;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
		if (savedCategory != null)
			throw new APIException("Category with the name " + category.getCategoryName() + " already exists!");
		Category save = categoryRepository.save(category);
		CategoryDTO savedCategoryDTO = modelMapper.map(save, CategoryDTO.class);
		return savedCategoryDTO;
	}

	@Override
	public CategoryDTO deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		categoryRepository.delete(category);
		CategoryDTO deletedCategory = modelMapper.map(category, CategoryDTO.class);
		return deletedCategory;
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		category.setCategoryId(categoryId);
		CategoryDTO savedCategoryDTO = modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
		return savedCategoryDTO;
	}
}
