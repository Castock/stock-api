package com.castock.stockdb.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockFlucEntity;

import java.util.List;

@Repository
public interface StockFlucRepository extends JpaRepository<StockFlucEntity, String>{

	List<StockFlucEntity> findByStockid(int stockid);
}