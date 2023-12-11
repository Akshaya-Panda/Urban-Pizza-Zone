package com.comviva.pos.service;

import java.util.ArrayList;

import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.StoreBean;
import com.comviva.pos.dao.AdministratorDao;

public class Administrator {
	
public static AdministratorDao adao=new AdministratorDao();

public String addStore(StoreBean storebean) {
	//Return value must be either: "SUCCESS", "FAIL", “ERROR”
	return adao.addStore(storebean);
}
	
public boolean modifyStore(StoreBean storebean) {
	return adao.modifyStore(storebean);
}

public ArrayList<StoreBean> viewAllStore() {
	
	return adao.viewAllStore();
}

public int removeStore(StoreBean sb)
{
	return adao.removeStore(sb);
	}

public String addFood(FoodBean fb)
{
return adao.addFood(fb);
}

public Boolean modifyFood(FoodBean fb) {
	return adao.modifyFood(fb);
}

public int removeFood(FoodBean fb) {
	return adao.removeFood(fb);
}


public ArrayList<FoodBean> viewAllFood() {
	
	return adao.viewAllFood();
}

}

