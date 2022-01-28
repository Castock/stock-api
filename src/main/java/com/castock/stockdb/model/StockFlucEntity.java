package com.castock.stockdb.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "stock")
public class StockFlucEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @Column(name = "stockid")
    private StockEntity stockid;

    @Column(name = "stockdate")
    private java.sql.Date stockdate;

    @Column(name = "highprice")
    private int highprice;

    @Column(name = "lowprice")
    private int lowprice;

    @Column(name = "startprice")
    private int startprice;

    @Column(name = "endprice")
    private int endprice;

    @Column(name = "fprice")
    private int fprice;

    @Column(name = "frate")
    private float frate;
    //private String frate;
}
