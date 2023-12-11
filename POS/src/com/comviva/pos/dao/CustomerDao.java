package com.comviva.pos.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.comviva.pos.bean.CartBean;
import com.comviva.pos.bean.CredentialsBean;
import com.comviva.pos.bean.CreditCardBean;
import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.OrderBean;
import com.comviva.pos.bean.ProfileBean;
import com.comviva.pos.bean.StoreBean;
import com.comviva.pos.util.DBUtil;

public class CustomerDao {
	
	public static Connection con= DBUtil.con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	CredentialsBean crBean=new CredentialsBean();
	StoreBean sb=new StoreBean();
	
	public String addToCart(CartBean cb, CredentialsBean cr, FoodBean fb) {
		int i=0;
		
		try {
			ps=con.prepareStatement("insert into pos_tbl_cart values(?,?,?,?,?,?,?)");
			ps.setInt(1, cb.getCartID());
			ps.setString(2, cr.getUserID());
			ps.setString(3, fb.getFoodID());
			ps.setString(4, cb.getType());
			ps.setInt(5, cb.getQuantity());
			ps.setDouble(6, cb.getCostl());
			ps.setDate(7,cb.getOrderdate());
						
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
	
	
	public Boolean modifyCart(CartBean ctBean, CredentialsBean crBean, FoodBean fb) {
		int i=0;
		
		try {
			ps=con.prepareStatement("update pos_tbl_cart set Userid=?,FoodId=?,Type=?,Quantity=?,Cost=?,OrderDate=? where CartId=?");
			
			ps.setString(1, ctBean.getUserID());
			ps.setString(2, fb.getFoodID());
			ps.setString(3, ctBean.getType());
			ps.setInt(4, ctBean.getQuantity());
			ps.setDouble(5, ctBean.getCostl());
			ps.setDate(6,ctBean.getOrderdate());
			ps.setInt(7, ctBean.getCartID());
						
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
	
	public int deleteCart(CartBean ctBean) {
		int i=0;
		
		try
		{
			ps=con.prepareStatement("delete from pos_tbl_cart where CartId=?");
			ps.setInt(1, ctBean.getCartID());
			
			i=ps.executeUpdate();
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}

		return i;
	}
	
	public ArrayList<CartBean> viewCart(CredentialsBean crBean) {
		ArrayList<CartBean> arcb=new ArrayList<CartBean>();
		
		try
		{
			ps=con.prepareStatement("select * from pos_tbl_cart where Userid=?");
			ps.setString(1, crBean.getUserID());
			rs= ps.executeQuery();
			
			while(rs.next()) {
	            CartBean cb=new CartBean();
	            cb.setExp(rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4)+"    "+rs.getString(5)+"    "+rs.getString(6)+"    "+rs.getString(7));

	            arcb.add(cb);
	            }
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return arcb;
	}
	
	
	public ArrayList<CartBean> viewAllCart() {
		ArrayList<CartBean> arcb=new ArrayList<CartBean>();
		
		try
		{
			ps=con.prepareStatement("select * from pos_tbl_cart");
			rs= ps.executeQuery();
			
			while(rs.next()) {
	            CartBean cb=new CartBean();
	            cb.setExp(rs.getString(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4)+"    "+rs.getString(5)+"    "+rs.getString(6)+"    "+rs.getString(7));
	            arcb.add(cb);
	            }
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}	
		return arcb;
	}
	

	
	public String createProfile(CredentialsBean crBean, ProfileBean pb) {
		int i=0;
		
		try {
			ps=con.prepareStatement("insert into pos_tbl_user_profile values(?,?,?,?,?,?,?,?,?,?,?,?)");
																							
			ps.setString(1, crBean.getUserID());
			ps.setString(2, pb.getFirstName());
			ps.setString(3, pb.getLastName());
			ps.setDate(4, pb.getDateOfBirth());
			ps.setString(5, pb.getGender());
			ps.setString(6, pb.getStreet());
			ps.setString(7, pb.getLocation());
			ps.setString(8, pb.getCity());
			ps.setString(9, pb.getState());
			ps.setString(10, pb.getPincode());
			ps.setString(11, pb.getMobileNo());
			ps.setString(12, pb.getEmailID());
						
			i=ps.executeUpdate();
		} 
		catch (SQLException sql) {
			System.out.println(sql);
		}
		
		if (i==1) {
			return "Registration SUCCESS";
		} else if (i==0){
			return "Registration FAIL";
		}
		
		else {
			return "ERROR";
		}
	}
	
	
	public static boolean authCustomer(String uid) {
		try {
			ps = con.prepareStatement("select userid from pos_tbl_user_profile where userid = ?");
			ps.setString(1, uid);
			rs = ps.executeQuery();
			String uid_tbl = "";
			
			while(rs.next()) {
				uid_tbl += rs.getString(1);
			}
			if(uid_tbl.equals(uid)) {
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
	
	
public String confirmOrder(OrderBean ob, CartBean ctBean,String usid) {
		
		int i=0;
		
		try {
			ps=con.prepareStatement("insert into pos_tbl_order values(?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, ob.getOrderID());
			ps.setString(2, usid);
			ps.setDate(3, ob.getOrderdate());
			ps.setString(4, ob.getStoreID());
			ps.setInt(5, ob.getCartID());
			ps.setString(6, "Order Dispatched");
			ps.setString(7, ob.getStreet());
			ps.setString(8, ob.getCity());
			ps.setString(9, ob.getState());
			ps.setString(10, ob.getPincode());
			ps.setString(11, ob.getMobileNo());
						
			i=ps.executeUpdate();
		} 
		catch (SQLException sql) {
			System.out.println(sql);
		}
		
		if (i==1) {
			return "Your Order Ready";
		} else if (i==0){
			return "Order cannot be processed";
		}
		else {
			return "ERROR!!!";
		}
		
	}

	
public void addCard(CreditCardBean ccb,String usid) {
	try {
		ps=con.prepareStatement("insert into pos_tbl_creditcard values(?,?,?,?,?)");
		
		ps.setString(1, ccb.getCcNo());
		ps.setString(2, ccb.getValidFrom());
		ps.setString(3, ccb.getValidTo());
		ps.setInt(4, ccb.getBalance());
		ps.setString(5, usid);
					
		ps.executeUpdate();
	} 
	catch (SQLException sql) {
		System.out.println(sql);
	}
	
}


public static boolean crCardPayment(CartBean ctBean, String ccNo) {

	int cost=0;
	int crBalance=0;
	int newBalance=0;
	int i=0;
	try {
		ps = con.prepareStatement("select Cost from pos_tbl_cart where CartId = ?");
		ps.setInt(1, ctBean.getCartID());
		rs = ps.executeQuery();		
		if(rs.next()) {
			cost=rs.getInt(1);
		}
		
		ps = con.prepareStatement("select Balance from pos_tbl_creditcard where creditcardnumber = ?");
		ps.setString(1, ccNo);
		rs = ps.executeQuery();
		if(rs.next()) {
			crBalance=rs.getInt(1);
		}
	
	JOptionPane.showMessageDialog(null,"Making Payment of RS- "+cost+"\n"+"Current Balance: RS- "+crBalance);
		
		newBalance=crBalance-cost;
		
		ps=con.prepareStatement("update pos_tbl_creditcard set Balance=? where creditcardnumber=?");
		ps.setInt(1, newBalance);
		ps.setString(2, ccNo);
	i=	ps.executeUpdate();
		


	
		JOptionPane.showMessageDialog(null,"Payment SUCCESSFUL"+"\n"+"Current Balance: RS- "+newBalance);
		JOptionPane.showMessageDialog(null,"Thanks For Ordering in  URBAN PIZZA-ZONE"+"\n"+"Visit Again!!");
		
	} catch (SQLException sql) {
		// TODO Auto-generated catch block
		System.out.println(sql);	
	}
	if(i==1)
	{
		return true;
	}
	else
	{
		return false;
	}
	
	
}


public static boolean authCreditCard(String ccNo) {
	try {
		ps = con.prepareStatement("select creditcardnumber from pos_tbl_creditcard where creditcardnumber=?");
		ps.setString(1, ccNo);
		rs=ps.executeQuery();
		String ccn_tbl = "";
		
		while(rs.next()) {
			ccn_tbl += rs.getString(1);
		}
		if(ccn_tbl.equals(ccNo)) {
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
