package com.stocks.service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stocks.service.domain.StockData;
import com.stocks.service.repository.StockRepository;
import com.stocks.service.repository.entity.Stocks;

public class StockServiceImpl implements StockService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

	private StockRepository stockRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<StockData> findStock(String stock) throws StockException {
		if(stock.isEmpty() || stockRepository.findByStock(stock).isEmpty()) {
			LOGGER.error("Couldn't find the stock");
			throw new StockException("Couldn't find the stock");
		}
		LOGGER.debug("Returning the stock: " + stock);
		return stockRepository.findByStock(stock);
	}

	@Override
	public void recordStockData(StockData stockData) {
		try {
			Stocks stock = mapper.map(stockData, Stocks.class);
			LOGGER.debug("Saving the stock: " + stock.getStock());
			stockRepository.save(stock);
		}catch(Exception e) {
			LOGGER.error("Unable to record the stock");
			e.printStackTrace();
		}
	}

}
