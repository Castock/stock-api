package com.castock.stockdb.dto;


import java.util.List;

import com.castock.stockdb.model.StockEntity;
import com.castock.stockdb.model.StockIndexEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDTO {
    private int id;
    private String stockcode;
    private String stockname;
    private StockIndexEntity stockidx;

    public StockDTO(final StockEntity entity){
        this.id = entity.getId();
        this.stockcode = entity.getStockcode();
        this.stockname = entity.getStockname();
        this.stockidx = entity.getStockidx();
    }

    //변환 : DTO to Entity
    public static StockEntity toEntity(final StockDTO dto){
        return StockEntity.builder()
                .id(dto.getId())
                .stockcode(dto.getStockcode())
                .stockname(dto.getStockname())
                .stockidx(dto.getStockidx())
                .build();
    }
}