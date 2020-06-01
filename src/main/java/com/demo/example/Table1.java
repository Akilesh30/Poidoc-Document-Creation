package com.demo.example;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Table1 {
	Table1(){
	}
	
	@Id
	
	private int Id;
	
	
	private String tableName;
	
	private String rows1;
	
	private String column1;
	public int getId() {
		return Id;
	}
	public void setId(int tableId) {
		this.Id = tableId;
	}
	public String gettableName() {
		return tableName;
	}
	public void settableName(String tableName) {
		this.tableName = tableName;
	}
	public String getrows1() {
		return rows1;
	}
	public void setrows1(String rows1) {
		this.rows1 = rows1;
	}
	public String getcolumn1() {
		return column1;
	}
	public void setcolumn1(String column1) {
		this.column1 = column1;
	}
	
	@Override
	public String toString() {
		return "Table1 [Id=" + Id + ", tableName=" + tableName + ", rows1=" + rows1 + ", column1="
				+ column1 + "]";
	}
	
}

