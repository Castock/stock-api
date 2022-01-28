package com.castock.stockdb.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.castock.stockdb.dto.ResponseDTO;
import com.castock.stockdb.dto.StockDTO;
import com.castock.stockdb.dto.StockFlucDTO;
import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockFlucEntity;
import com.castock.stockdb.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("castock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping("/insert")
	public ResponseEntity<?> insertStock(@RequestBody StockDTO dto, RequestBody StockFlucDTO flucs) {
		try {
			StockEntity entity = StockDTO.toEntity(dto);
			List<StockEntity> stock = stockService.insertStock(entity);
			List<StockDTO> dtos = stock.stream().map(StockDTO::new).collect(Collectors.toList());
			ResponseDTO<StockDTO> response = ResponseDTO.<StockDTO>builder().data(dtos).build();

            StockFlucEntity stockFluc= StockFlucDTO.toEntity(flucs);
			List<StockEntity> fluc = stockService.insertStockFluc(stockfluc);
			List<StockDTO> flucDTOs = fluc.stream().map(StockFlucDTO::new).collect(Collectors.toList());

			return ResponseEntity.ok().body(response);

		} catch (Exception e) {

			String error = e.getMessage();
			ResponseDTO<StockDTO> response = ResponseDTO.<StockDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchStock(@RequestParam(required = true) String stockName) {
        List<StockEntity> stock = stockService.searchStock(stockName);
        List<StockDTO> stockDTO = stock.stream().map(StockDTO::new).collect(Collectors.toList());
        ResponseDTO<StockDTO> res = ResponseDTO.<StockDTO>builder().data(stockDTO).build();

        return ResponseEntity.ok().body(res);
	}
}