package com.fastfood.api.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.CategoryService;
import com.fastfood.utils.FileUploadUtil;

@RestController(value = "productAPIOfAdmin")
@RequestMapping("admin/api/v1/products")
public class ProductAPI {

	@Autowired
	IProductService productService;

	@Autowired
	FileUploadUtil fileUploadUtil;

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<ProductDTO> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		ProductDTO result = new ProductDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(productService.findAllByStatus(pageable, SystemConstant.ACTIVE_STATUS));
		result.setTotalItem(productService.getTotalItem());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));


		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	@Transactional
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("productId") long productId) {

		ApiResponse response = productService.softDelete(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<ProductDTO> addNewProduct(@RequestParam("files") MultipartFile[] files,
			HttpServletRequest request, @RequestParam("productName") String productName,
			@RequestParam("price") double price, @RequestParam("salePrice") double salePrice,
			@RequestParam("description") String description, @RequestParam("category") String category) {

		ProductDTO result = new ProductDTO();
		CategoryDTO cateDTO = categoryService.findByType(category);

		result.setProductName(productName);
		result.setCategoryDTO(cateDTO);
		result.setPrice(price);
		result.setSalePrice(salePrice);
		result.setDescription(description);
		result.setStatus(SystemConstant.ACTIVE_STATUS);
		result = productService.save(result);

		if (result != null) {
			String img = fileUploadUtil.saveFiles(files, request, result.getId());
//			result.setImg(img);
			productService.save(result);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping()
	public ResponseEntity<ProductDTO> updateProduct(
			@RequestParam(value = "files") MultipartFile[] files,
			HttpServletRequest request, @RequestParam("productName") String productName, @RequestParam("id") long id,
			@RequestParam("price") double price, @RequestParam("salePrice") double salePrice,
			@RequestParam("description") String description, @RequestParam("category") String category) {

		ProductDTO result = new ProductDTO();
		CategoryDTO cateDTO = categoryService.findByType(category);
		result.setId(id);
		result.setProductName(productName);
		result.setCategoryDTO(cateDTO);
		result.setPrice(price);
		result.setSalePrice(salePrice);
		result.setDescription(description);
		result.setStatus(SystemConstant.ACTIVE_STATUS);

		if (files != null) {
			String img = fileUploadUtil.saveFiles(files, request, id);
//			result.setImg(img);
		}

		productService.save(result);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
