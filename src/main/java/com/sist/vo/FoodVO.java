package com.sist.vo;

import lombok.Data;

/**
 *  NO                                        NOT NULL NUMBER
 POSTER                                             VARCHAR2(260)
 PRICE                                              VARCHAR2(100)
 CNO                                                NUMBER
 NAME                                      NOT NULL VARCHAR2(100)
 TYPE                                      NOT NULL VARCHAR2(200)
 PHONE                                     NOT NULL VARCHAR2(20)
 ADDRESS                                   NOT NULL VARCHAR2(500)
 SCORE                                              NUMBER(2,1)
 THEME                                     NOT NULL CLOB
 TIME                                               VARCHAR2(50)
 CONTENT                                   NOT NULL CLOB
 
 * @author sist-13
 *
 */
@Data
public class FoodVO {
	private int no;
	private String name,address,phone,type,parking,poster,time,price,theme,content;
	private double score;
}
