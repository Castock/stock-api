package com.castock.stockdb.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockFlucEntity;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String>{

    @Query(value = "SELECT * FROM stock s WHERE s.stockname LIKE '%?1%'", nativeQuery = true)
	List<StockEntity> findByStockname(String stockname);
}