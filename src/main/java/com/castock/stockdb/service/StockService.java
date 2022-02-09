package com.castock.stockdb.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

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

    public String reqCrawling(){

        final String _REQURL = "http://localhost:12312/crawling/stockinfojson";

        try{
            URL url = new URL(_REQURL);
    
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //conn.setReadTimeout(1000 * 20);
            //conn.setConnectTimeout(1000 * 20);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    
            String inputLine;
            String result = new String();
            while((inputLine = in.readLine()) != null) { // response 출력
                result += inputLine;
            }
     
            in.close();
            //conn.disconnect();
            
            return result;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}