package com.ssgtarbucks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssgtarbucks.domain.IncomeDTO;
import com.ssgtarbucks.domain.MoveItemDTO;
import com.ssgtarbucks.domain.SaleDTO;
import com.ssgtarbucks.domain.StockDTO;
import com.ssgtarbucks.domain.StockLocationDTO;
import com.ssgtarbucks.service.StockService;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin("*")

public class StockController {

	@Autowired
	private StockService stockService;
	
	@GetMapping("/list")
	public ResponseEntity<List<StockDTO>> stockList (@RequestParam String branch_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);
		
		List<StockDTO> stockList = stockService.selectStorageByBranchId(branch_id);
		System.out.println("StockController - /stock/list/stockList(get) >>> stockList :" + stockList);

        return ResponseEntity.ok(stockList);
    }
	
	@PutMapping("/quantity")
	public ResponseEntity<List<StockDTO>> changeQuantity (@RequestParam String branch_id, @RequestBody StockDTO stockDTO ) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);
		System.out.println(stockDTO.getItem_id());
		System.out.println(stockDTO.getStock_quantity());

		int result = stockService.updateStockQuantityByItemId(stockDTO.getStock_quantity(), stockDTO.getItem_id());
		if(result > 0) {
			System.out.println("StockController - /stock/list/quantity(put) >>> 수량정정성공");
		}
				
		List<StockDTO> stockList = stockService.selectStorageByBranchId(branch_id);
		System.out.println("StockController - /stock/list/quantity(put) >>> stockList :" + stockList);

        return ResponseEntity.ok(null);
    }
	
	@GetMapping("/sale/list")
	public ResponseEntity<List<SaleDTO>> saleList (@RequestParam String branch_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);
		
		List<SaleDTO> saleList = stockService.selectSaleListByBranchId(branch_id);
		System.out.println("StockController - /stock/sale/list/stockList(get) >>> stockList :" + saleList);

        return ResponseEntity.ok(saleList);
    }
	
	@PutMapping("/sale/product")
	public ResponseEntity<List<SaleDTO>> changeQuantity (@RequestParam String branch_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);

		
		List<SaleDTO> saleList = stockService.selectSaleListByBranchId(branch_id);
		
		stockService.updateSaleTransaction(branch_id, saleList);

		System.out.println("StockController - /stock/list/quantity(put) >>> stockList :" + saleList);

        return ResponseEntity.ok(null);
    }

	@GetMapping("/checked/inspection")
	public ResponseEntity<List<IncomeDTO>> inspectionList (@RequestParam String branch_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + branch_id);

		List<IncomeDTO> inspectionList = stockService.selectInspectionListByBranchId(branch_id);
		
		System.out.println(inspectionList);
		
        return ResponseEntity.ok(inspectionList);
    }

	//QR코드
	@GetMapping("/checked/insert/location/qr")
	public ResponseEntity<List<IncomeDTO>> insertLocationQR (@RequestParam String scanResult, @RequestParam int item_id) { 
		System.out.println("branch_id>>>>>>>>>>>>" + scanResult+"|"+item_id);
		
		StockLocationDTO stockLocationDTO = stockService.selectStockLocationByLocationCode(scanResult);
		
		StockDTO stockDTO = stockService.selectStockByItemId(item_id);
		
		int result1 = stockService.updateStockLocation(stockLocationDTO.getLocation_id(), item_id);
		System.out.println(stockLocationDTO);

        return ResponseEntity.ok(null);
    }
	
	//수기
	@GetMapping("/checked/insert/location")
	public ResponseEntity<List<IncomeDTO>> insertLocation (@RequestParam int location_id, @RequestParam int item_id) { 
		System.out.println("/checked/insert/location>>>>>>>>>>>>"+location_id+item_id);

		int result = stockService.updateStockLocation(location_id, item_id);
		
        return ResponseEntity.ok(null);
    }
	
	@GetMapping("/checked/show/location")
	public ResponseEntity<List<StockLocationDTO>> selectStockLocationList (@RequestParam String branch_id) { 

		List<StockLocationDTO> stockLocationList = stockService.selectStockLocationByBranchId(branch_id);
		
        return ResponseEntity.ok(stockLocationList);
    }



	@PutMapping("/location/move")
		public ResponseEntity<List<SaleDTO>> moveItems (@RequestParam String branch_id, @RequestBody MoveItemDTO moveItemDTO) { 
			System.out.println("StockController - /stock/location/move(put) >>> MoveItemDTO :" + moveItemDTO);
			
			int result = stockService.updateStockByItemIdToMove(moveItemDTO);
			
	        return ResponseEntity.ok(null);
	    }
	
	
}
