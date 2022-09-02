package com.stocks.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stocks.service.domain.StockData;

@Service
public interface StockService {
	
	List<StockData> findStock(String stock) throws StockException;
	
	void recordStockData(StockData stockData);

}
