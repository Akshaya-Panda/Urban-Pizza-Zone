package com.comviva.pos.bean;

import java.sql.Date;
//import java.util.Date;

public class CartBean {
	private int cartID,quantity;
	private  Date orderdate;
	private double costl;
	private String userID, FooDid,type,exp;
	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getCostl() {
		return costl;
	}
	public void setCostl(double costl) {
		this.costl = costl;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFooDid() {
		return FooDid;
	}
	public void setFooDid(String fooDid) {
		FooDid = fooDid;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate( Date date) {
		this.orderdate = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExp() {
		return exp;
	}
	

	public void setExp(String exp) {
		this.exp = exp;
	}

	
	
}
