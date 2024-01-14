package com.fastfood.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.service.ICategoryService;

@RestController
@RequestMapping("/admin/api/v1")
public class CategoryAPI {

	@Autowired
	private ICategoryService cateService;

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> loadAllCategory() {
		return new ResponseEntity<>(cateService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/category/{name}")
	public ResponseEntity<CategoryDTO> findByName(@PathVariable String name) {
		return new ResponseEntity<>(cateService.findByType(name), HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<>(cateService.save(categoryDTO), HttpStatus.OK);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<ApiResponse> disableCategoryByID(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.softDeleteCategory(id), HttpStatus.OK);
	}

	@PostMapping("/category/{id}")
	public ResponseEntity<ApiResponse> activeCategoryByID(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.activeCategory(id), HttpStatus.OK);
	}
}