package com.ssgtarbucks.service;

import java.util.List;

import com.ssgtarbucks.domain.ProductDTO;

public interface ProductService {

	public List<ProductDTO> selectProductListByBranchId(String branch_id);

}
