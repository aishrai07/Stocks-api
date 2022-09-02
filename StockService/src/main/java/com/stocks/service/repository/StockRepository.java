package com.stocks.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.service.domain.StockData;
import com.stocks.service.repository.entity.Stocks;

	
@Repository
public interface StockRepository extends JpaRepository<Stocks, String>
{
	List<StockData> findByStock(String stock);
}

