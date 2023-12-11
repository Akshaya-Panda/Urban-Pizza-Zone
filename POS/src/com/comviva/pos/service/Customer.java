package com.comviva.pos.service;

import java.util.ArrayList;

import com.comviva.pos.bean.CartBean;
import com.comviva.pos.bean.CredentialsBean;
import com.comviva.pos.bean.CreditCardBean;
import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.OrderBean;
import com.comviva.pos.bean.ProfileBean;
import com.comviva.pos.dao.CustomerDao;

public class Customer {
	public static CustomerDao cdao=new CustomerDao();

	public String addToCart(CartBean ctBean, CredentialsBean crBean, FoodBean fb) {
		return cdao.addToCart(ctBean,crBean,fb);
	}

	public Boolean modifyCart(CartBean ctBean, CredentialsBean crBean, FoodBean fb) {
		return cdao.modifyCart(ctBean,crBean,fb);
	}

	public int deleteCart(CartBean ctBean) {
		return cdao.deleteCart(ctBean);
	}

	public ArrayList<CartBean> viewCart(CredentialsBean crBean) {
		
		return cdao.viewCart(crBean);
	}

	public ArrayList<CartBean> viewAllCart() {
		return cdao.viewAllCart();
	}


	
	
	
	

	public String createProfile(CredentialsBean crBean, ProfileBean pb) {

		return cdao.createProfile(crBean,pb);
	}

	public String confirmOrder(OrderBean ob, CartBean ctBean,String usid) {
	
		return cdao.confirmOrder(ob,ctBean,usid);
	}

	public void addCard(CreditCardBean ccb,String usid) {
	
		cdao.addCard(ccb,usid);
	}

}
