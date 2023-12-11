package com.comviva.pos.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.comviva.pos.bean.CredentialsBean;
import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.StoreBean;
import com.comviva.pos.service.Administrator;
import com.comviva.pos.util.DBUtil;

public class AdministratorDao extends Administrator{
public static Connection con=DBUtil.con;
public static PreparedStatement ps;
public static ResultSet rs;
int i=0;
	@Override
	public String addStore(StoreBean storebean) {
		try
		{
		ps=con.prepareStatement("insert into POS_TBL_PizzaStore values(?,?,?,?,?,?,?)");
		ps.setString(1,storebean.getStoreID());
		ps.setString(2, storebean.getName());
		ps.setString(3,storebean.getStreet());
		ps.setString(4,storebean.getMobileNo());
		ps.setString(5, storebean.getCity());
		ps.setString(6, storebean.getState());
		ps.setString(7, storebean.getPincode());
		i=ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			return "ERROR";
		}
		if(i==1)
		{
			return "SUCCESS";
		}
		else
		{
			return "FAIL";
		}
	}
	
	
	public int removeStore(StoreBean sb) {
		int i=0;
		
		try
		{
			ps=con.prepareStatement("delete from pos_tbl_pizzastore where StoreId=?");
			ps.setString(1, sb.getStoreID());
			
			i=ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}

		return i;
	}
	
	
	public boolean modifyStore(StoreBean sb) {	
		int i = 0;
		try {
			ps=con.prepareStatement("update pos_tbl_pizzastore set Name=?,Street=?,MobileNo=?,City=?,State=?,Pincode=? where StoreId=?");
			
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getStreet());
			ps.setString(3, sb.getMobileNo());
			ps.setString(4, sb.getCity());
			ps.setString(5, sb.getState());
			ps.setString(6, sb.getPincode());
			ps.setString(7, sb.getStoreID());
			
			i=ps.executeUpdate();
		} 
		catch (SQLException sql) {
			System.out.println(sql);
		}
		
		if (i==1) {
			JOptionPane.showMessageDialog(null, "Update SUCCESSFUL");
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Update UN-SUCCESSFUL");
			return false;
		}
	}


	public ArrayList<StoreBean> viewAllStore() {
		ArrayList<StoreBean> aStoreBeans=new ArrayList<StoreBean>();
		
		try
		{
			ps=con.prepareStatement("select StoreId,Name,MobileNo,State from pos_tbl_pizzastore");
			rs= ps.executeQuery();
	
	            while(rs.next()) {
	            StoreBean sb=new StoreBean();
	            sb.setExp(rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));

	            aStoreBeans.add(sb);
	            }
			
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		
		return aStoreBeans;
	}
	
	
	
	
	public String addFood(FoodBean fb) {
		int i=0;
		
		try {
			ps=con.prepareStatement("insert into pos_tbl_food values(?,?,?,?,?,?)");
			
			ps.setString(1, fb.getFoodID());
			ps.setString(2, fb.getName());
			ps.setString(3, fb.getType());
			ps.setString(4, fb.getFoodSize());
			ps.setInt(5, fb.getQuantity());
			ps.setDouble(6, fb.getPrice());
			
			i=ps.executeUpdate();
		} 
		catch (SQLException sql) {
			System.out.println(sql);
		}
		
		if (i==1) {
			return "SUCCESS";
		} else if (i==0){
			return "FAIL";
		}
		else {
			return "ERROR";
		}
	}
	
	public Boolean modifyFood(FoodBean fb) {
		int i=0;
		try {
		ps=con.prepareStatement("update pos_tbl_food set Name=?,Type=?,FoodSize=?,Quantity=?,Price=? where FoodId=?");
		
		ps.setString(6, fb.getFoodID());
		ps.setString(1, fb.getName());
		ps.setString(2, fb.getType());
		ps.setString(3, fb.getFoodSize());
		ps.setInt(4, fb.getQuantity());
		ps.setDouble(5, fb.getPrice());
		
		i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		
		if (i==1) {
			JOptionPane.showMessageDialog(null, "Update SUCCESSFUL");
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Update UN-SUCCESSFUL");
			return false;
		}
	}
	
	
	public int removeFood(FoodBean fb) {
		int i=0;
		
		try
		{
			ps=con.prepareStatement("delete from pos_tbl_food where FoodId=?");
			ps.setString(1, fb.getFoodID());
			
			i=ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}

		return i;
	}

	
	
	public ArrayList<FoodBean> viewAllFood() {
		
		ArrayList<FoodBean> aFoodBeans=new ArrayList<FoodBean>();
		
		try
		{
			ps=con.prepareStatement("select FoodId,Name,Quantity,Price from pos_tbl_food");
			rs= ps.executeQuery();
			
			while(rs.next()) {
	            FoodBean fb=new FoodBean();
	            fb.setExp(rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));

	            aFoodBeans.add(fb);
	            }
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return aFoodBeans;
	}
	

	public static FoodBean viewFood(String foodId) {
		
FoodBean aFoodBeans=new FoodBean();
try
		{
			ps=con.prepareStatement("select * from pos_tbl_food where FoodID=?");
			ps.setString(1, foodId);
			rs= ps.executeQuery();
			
			if(rs.next()) {
	           
	            aFoodBeans.setExp(rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));

	          
	            }
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return aFoodBeans;
	}
	
	


public static boolean authenticate(CredentialsBean crBean) {
	try {
		ps = con.prepareStatement("select Userid,password from pos_tbl_user_credentials where Userid = ?");
		ps.setString(1, crBean.getUserID());
		rs = ps.executeQuery();
		String strTbl = "";
		
		while(rs.next()) {
			strTbl += rs.getString(1)+rs.getString(2);
		}
		
		if(strTbl.equals(crBean.getStr())) {
			return true;
		}else {
			return false;
		}			
	}
	catch (SQLException sql) {
		System.out.println(sql);
	}
	
	return false;
}
}
