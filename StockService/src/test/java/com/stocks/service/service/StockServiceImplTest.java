package com.stocks.service.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import com.stocks.service.domain.StockData;
import com.stocks.service.repository.StockRepository;
import com.stocks.service.repository.entity.Stocks;
import com.stocks.service.service.StockException;
import com.stocks.service.service.StockServiceImpl;

public class StockServiceImplTest {
	
	@InjectMocks
	StockServiceImpl stockServiceImpl;
	
	@Mock
	StockData stockData;
	
	@Mock
	ModelMapper mapper;
	
	@Mock
	Stocks stocks;
	
	@Mock
	StockRepository stockRepository;
	
	@Mock
	List<StockData> stockDatalist;
	
	@Test
	public void testRecordStockData() {
		when(mapper.map(stockData, Stocks.class)).thenReturn(stocks);
		doNothing().when(stockRepository).save(stocks);
		verify(stockServiceImpl,times(1)).recordStockData(stockData);
	}
	
	@Test
	public void testfindStockNegative() {
		String stock = "";
		assertThrows(StockException.class, ()->stockServiceImpl.findStock(stock));
	}
	
	@Test
	public void testfindStock() throws StockException {
		String stock = "AA";
		when(stockRepository.findByStock(stock)).thenReturn(stockDatalist);
		assertNotNull(stockServiceImpl.findStock(stock));
	}

}
