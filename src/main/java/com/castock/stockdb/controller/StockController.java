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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@RestController
@RequestMapping("castock")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/insert")
	public ResponseEntity<?> insertStock() {
		
		JSONParser parser = new JSONParser();
		String jsonStr = stockService.reqCrawling();
		//return "no1" + jsonStr;
		
		try{
			Object obj = parser.parse( jsonStr );
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray dataArr = (JSONArray) jsonObj.get("data");
	
			for (int i = 0; i < dataArr.size(); i++){
				JSONObject sep = (JSONObject) dataArr.get(i);
				//{"id":439,"stockcode":"174900","stockdate":"2022-01-29","stockname":"앱클론","stockidx":1,"highprice":14100,"lowprice":12850,"startprice":13150,"endprice":13700,"fprice":750,"frate":5.79}
				StockEntity entity = new StockEntity();
	
				entity.setStockcode((String)sep.get("stockcode"));
				entity.setStockname((String)sep.get("stockname"));
				entity.setStockidx((int)(long)(sep.get("stockidx")));
				//logger.warn(entity.toString());

				stockService.insertStock(entity);
			}
					
			ResponseDTO<StockDTO> response = ResponseDTO.<StockDTO>builder().build();

			return ResponseEntity.ok().body(response);
			
		}catch(Exception e){

			ResponseDTO<StockDTO> response = ResponseDTO.<StockDTO>builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(response);
		}
		


			//StockFlucEntity stockFluc= StockFlucDTO.toEntity(flucs);
			//stockService.insertStockFluc(stockFluc);
		
		/*
		List<StockEntity> stock = 
		List<StockDTO> dtos = stock.stream().map(StockDTO::new).collect(Collectors.toList());
		

		
		List<StockFlucEntity> fluc = 
		//List<StockFlucDTO> flucDTOs = fluc.stream().map(StockFlucDTO::new).collect(Collectors.toList());*/

	}

	@GetMapping("/search")
	public ResponseEntity<?> searchStock(@RequestParam(required = true) String stockName) {
        List<StockEntity> stock = stockService.searchStock(stockName);
        List<StockDTO> stockDTO = stock.stream().map(StockDTO::new).collect(Collectors.toList());
        ResponseDTO<StockDTO> res = ResponseDTO.<StockDTO>builder().data(stockDTO).build();

        return ResponseEntity.ok().body(res);
	}
}