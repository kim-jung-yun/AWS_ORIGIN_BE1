package com.ssgtarbucks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssgtarbucks.domain.IncomeDTO;
import com.ssgtarbucks.domain.ProductDTO;
import com.ssgtarbucks.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductDTO>> incomeList (@RequestParam String branch_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);
				
		List<ProductDTO> productList = productService.selectProductListByBranchId(branch_id);
		System.out.println("ProductController - /product/list/productList(get) >>> productList :" + productList);

        return ResponseEntity.ok(productList);
    }

}
