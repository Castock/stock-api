package com.castock.stockdb.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockFlucEntity;
import com.castock.stockdb.persistence.StockFlucRepository;
import com.castock.stockdb.persistence.StockRepository;


@Service
public class StockService {

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private StockFlucRepository flucRepo;

    public List<StockEntity> insertStock(final StockEntity stock){

        stockRepo.save(stock);     

        return stockRepo.findByStockname(stock.getStockname());
    }

    public List<StockFlucEntity> insertStockFluc(final StockFlucEntity fluc){

        flucRepo.save(fluc);

        return flucRepo.findByStockid(fluc.getStockid());
    }

    public List<StockEntity> searchStock(String stockName){
        return stockRepo.findByStockname(stockName);
    }

    private void validate(final StockEntity stock) {
		if(stock == null) {
			//log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
        
		if(stock.getStockname() == null) {
			//log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}