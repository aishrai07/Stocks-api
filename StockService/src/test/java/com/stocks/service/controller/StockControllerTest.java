package com.stocks.service.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.stocks.service.controller.StockController;
import com.stocks.service.domain.StockData;
import com.stocks.service.service.StockException;
import com.stocks.service.service.StockService;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

	@InjectMocks
	StockController stockController;
	@Mock
	StockService stockService;
	@Mock
	StockData stockData;
	
	@Test
	public void testAddStock() {
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		 doNothing().when(stockService).recordStockData(stockData);
		 StockData stockData = new StockData("1","1","AA","1/7/2011","15.82","16.72","15.78","16.42","239655616","3.79267","","","16.71","15.97","-4.42849","26","0.182704");
		 ResponseEntity<String> responseEntity = stockController.addStock(stockData);
		 assert(responseEntity.getStatusCode().equals(200));
	}
	
	@Test
	public void testAddStockNegative() {
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		 doNothing().when(stockService).recordStockData(stockData);
		 when(stockController.addStock(stockData)).thenThrow(NullPointerException.class);
		 StockData stockData = null;
		 ResponseEntity<String> responseEntity = stockController.addStock(stockData);
		 assert(responseEntity.getStatusCode().equals(400));
	}
	
	@Test
	public void testGetStock() throws StockException {
		StockData stockData = new StockData("1","1","AA","1/7/2011","15.82","16.72","15.78","16.42","239655616","3.79267","","","16.71","15.97","-4.42849","26","0.182704");
		when(stockService.findStock("AA")).thenReturn((List<StockData>) stockData);
		ResponseEntity<List<StockData>> responseEntity= stockController.getStock("AA");
		assert(responseEntity.hasBody());
	}
	
}
