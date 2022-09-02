package com.stocks.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.service.domain.StockData;
import com.stocks.service.service.StockException;
import com.stocks.service.service.StockService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@RequestMapping("/api")
public class StockController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
	
	private StockService stockService;
	
	@RequestMapping(value = "/stock", method = RequestMethod.POST)
	public ResponseEntity<String> addStock(@RequestBody StockData stockData){
		try {
			stockService.recordStockData(stockData);
			return ResponseEntity.ok("Successfully added !");
		}catch(NullPointerException e) {
			LOGGER.error("Stock data is Null",e);
            return ResponseEntity.badRequest().body("Stock Data is Null");
		}catch(Exception e) {
			LOGGER.error("Caused by: " + e.getCause() + " : " + e.getMessage());
			return ResponseEntity.badRequest().body("Caused by: " + e.getCause() + " : " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/stocks/{stock}", method = RequestMethod.GET)
	public ResponseEntity<List<StockData>> getStock(@PathVariable(required = true) String stock) throws StockException,NullPointerException{
		if(stock.isEmpty()) {
			LOGGER.error("Stock name not provided");
		}
		List<StockData> stockdata = stockService.findStock(stock);
		return new ResponseEntity<List<StockData>>(stockdata, HttpStatus.OK);
	} 

	

}
