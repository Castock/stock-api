package com.castock.stockdb.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockFlucEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockFlucDTO {

    private int id;
    private int stockid;
    private java.sql.Date stockdate;
    private int highprice;
    private int lowprice;
    private int startprice;
    private int endprice;
    private int fprice;
    private float frate;


    public StockFlucDTO(final StockFlucEntity entity){
        this.id = entity.getId();
        this.stockid= entity.getStockid();
        this.stockdate = entity.getStockdate();
        this.highprice= entity.getHighprice();
        this.lowprice = entity.getLowprice();
        this.startprice = entity.getStartprice();
        this.endprice = entity.getEndprice();
        this.fprice = entity.getFprice();
        this.frate = entity.getFrate();
    }

    //변환 : DTO to Entity
    public static StockFlucEntity toEntity(final StockFlucDTO dto){
        return StockFlucEntity.builder()
                .stockid(dto.getStockid())
                .stockdate(dto.getStockdate())
                .highprice(dto.getHighprice())
                .lowprice(dto.getLowprice())
                .startprice(dto.getStartprice())
                .endprice(dto.getEndprice())
                .fprice(dto.getFprice())
                .frate(dto.getFrate())
                .build();
    }
}