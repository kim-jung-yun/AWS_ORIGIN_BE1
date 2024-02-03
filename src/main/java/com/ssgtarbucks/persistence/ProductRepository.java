package com.ssgtarbucks.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssgtarbucks.domain.ProductDTO;
import com.ssgtarbucks.domain.StockDTO;

@Mapper
public interface ProductRepository {
		
	public List<ProductDTO> selectProductListByBranchId(String branch_id);
	
}