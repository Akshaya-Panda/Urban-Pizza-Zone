package com.comviva.pos.ui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.comviva.pos.bean.CartBean;
import com.comviva.pos.bean.CredentialsBean;
import com.comviva.pos.bean.CreditCardBean;
import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.OrderBean;
import com.comviva.pos.bean.ProfileBean;
import com.comviva.pos.bean.StoreBean;
import com.comviva.pos.dao.AdministratorDao;
import com.comviva.pos.dao.CustomerDao;
//import com.comviva.pos.dao.posdaocustomer;
import com.comviva.pos.service.Administrator;
import com.comviva.pos.service.Customer;
import com.comviva.pos.util.DBUtil;

public class CustomerUI {
	
	
	Administrator as = new Administrator();
	Customer cs = new Customer();
	public static Connection con=DBUtil.con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	StoreBean sb=new StoreBean();
	FoodBean fb=new FoodBean();
	CartBean ctBean=new CartBean();
	ProfileBean pb=new ProfileBean();
	CredentialsBean crBean=new CredentialsBean();
	OrderBean ob=new OrderBean();
	CreditCardBean ccb=new CreditCardBean();
	public static String userID;
	
	public void ValidateCustomer() {
		
		String choice=JOptionPane.showInputDialog("Registered Customer? [Y/N]");
		
		if(choice.equalsIgnoreCase("N")) {
		
			JOptionPane.showMessageDialog(null,"Initiating Customer Registration");
			
			userID=JOptionPane.showInputDialog("Enter User ID");
			crBean.setUserID(userID);
			String firstName=JOptionPane.showInputDialog("Enter Your First Name");
			pb.setFirstName(firstName);
			String lastName=JOptionPane.showInputDialog("Enter Your Last Name");
			pb.setLastName(lastName);
			Date dob = Date.valueOf(JOptionPane.showInputDialog("Enter DOB in (yyyy-mm-dd) Format"));
			pb.setDateOfBirth(dob);
			String gender=JOptionPane.showInputDialog("Enter Your Gender (M/F)");
			pb.setGender(gender);
			String street=JOptionPane.showInputDialog("Enter Street");
			pb.setStreet(street);
			String location=JOptionPane.showInputDialog("Enter Location");
			pb.setLocation(location);
			String city=JOptionPane.showInputDialog("Enter City");
			pb.setCity(city);
			String state=JOptionPane.showInputDialog("Enter State");
			pb.setState(state);
			String pincode=JOptionPane.showInputDialog("Enter Pincode");
			pb.setPincode(pincode);
			String mobileNo=JOptionPane.showInputDialog("Enter 10 digit Mobile No");
			pb.setMobileNo(mobileNo);
			String emailID=JOptionPane.showInputDialog("Enter Your Email");
			pb.setEmailID(emailID);
			
			JOptionPane.showMessageDialog(null,cs.createProfile(crBean,pb));
		
		}
		
		else if(choice.equalsIgnoreCase("Y")) {
			
			
			userID=JOptionPane.showInputDialog("Enter Registerd UserID");
			boolean flag = CustomerDao.authCustomer(userID);

			if(flag==true) {

					JOptionPane.showMessageDialog(null,"Customer Log-in SUCCESSFUL");
					
			String userId=JOptionPane.showInputDialog("Enter CUSTOMER UserID");
			crBean.setUserID(userId);
			String password=JOptionPane.showInputDialog("Enter CUSTOMER Password");
			crBean.setPassword(password);
			crBean.setStr(crBean.getUserID()+crBean.getPassword());
			
			boolean auth = AdministratorDao.authenticate(crBean);
			
	

			
		 if ((userId.equals("US-002")) && (auth)) {
			 ArrayList<FoodBean> aFoodBean=new ArrayList<FoodBean>();
			 String s="";
				aFoodBean=as.viewAllFood();
		
		 for(FoodBean o:aFoodBean) {
			 s +=o.getExp();
			 s +="\n";
		 }
		 
		 String fid=JOptionPane.showInputDialog(s+"Enter your prefered food id");
		 int quan=Integer.parseInt(JOptionPane.showInputDialog(s+"Enter Cart Quantity"));
				int cartID=Integer.parseInt(JOptionPane.showInputDialog("Enter Cart ID (Num only)"));
				ctBean.setCartID(cartID);
				String userID=userId; //JOptionPane.showInputDialog("Enter User ID");
				crBean.setUserID(userID);
				//String foodID=JOptionPane.showInputDialog("Enter Food ID");
				fb.setFoodID(fid);
				String type=abc1.abc(fid);
				ctBean.setType(type);
				JOptionPane.showMessageDialog(null, "The type of the food is "+type);
			//	int quantity=Integer.parseInt(JOptionPane.showInputDialog("Enter Cart Quantity"));
				ctBean.setQuantity(quan);
				int cost=abc1.abc2(fid);
				cost=cost*quan;
				ctBean.setCostl(cost);
				JOptionPane.showMessageDialog(null, "Total cost is "+cost);
				//LocalDate orderDate = LocalDate.now();
				//ctBean.setOrderdate(Date.from(orderDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			    Date orderDate=Date.valueOf((JOptionPane.showInputDialog(null, " OrderDate in (yyyy-mm-dd) Format \n")));
			    ctBean.setOrderdate(orderDate);
				
				JOptionPane.showMessageDialog(null,cs.addToCart(ctBean,crBean,fb));
		 }	
		
		
		else if ((userId.equals("US-003")) && (auth)) {			
			ArrayList<CartBean> arcb=new ArrayList<CartBean>();
			String str="";
			
			String userID=JOptionPane.showInputDialog("Enter User ID to see Cart");
			crBean.setUserID(userID);
			
			JOptionPane.showMessageDialog(null,"Showing All Cart Details of User"+crBean.getUserID());
			arcb=cs.viewCart(crBean);

			for (CartBean cb : arcb) {
				str+=cb.getExp()+"\n";
			}
			
			JOptionPane.showMessageDialog(null, str);		
			
		}
		
			
		
		else if ((userId.equals("US-004")) && (auth)) {
	
			String opt=JOptionPane.showInputDialog("Enter a Option [ Modify(M) / Delete(D) ] Cart ");
		
			 
		 
			if(opt.equalsIgnoreCase("M")) {
				
				ArrayList<CartBean> arcb=new ArrayList<CartBean>();
				 String s="";
					arcb=cs.viewAllCart();
			
			 for(CartBean o:arcb) {
				 s +=o.getExp();
				 s +="\n";
			 }
				
				int cartID=Integer.parseInt(JOptionPane.showInputDialog(s+ " Enter Cart ID to Modify (Num Only)"));
				ctBean.setCartID(cartID);
				String userID=JOptionPane.showInputDialog("Enter New User ID");
				crBean.setUserID(userID);
				String foodID=JOptionPane.showInputDialog(s+ "Enter New Food ID");
				fb.setFoodID(foodID);
				String type=JOptionPane.showInputDialog("Enter New Cart Type");
				ctBean.setType(type);
				int quantity=Integer.parseInt(JOptionPane.showInputDialog("Enter New Cart Quantity"));
				ctBean.setQuantity(quantity);
				int cost=abc1.abc2(foodID);
				ctBean.setCostl(cost);
				JOptionPane.showMessageDialog(null, "Total cost is "+cost);
				Date orderDate = Date.valueOf(JOptionPane.showInputDialog("Enter New OrderDate in (yyyy-mm-dd) Format"));
				ctBean.setOrderdate(orderDate);
				
				JOptionPane.showMessageDialog(null,cs.modifyCart(ctBean,crBean,fb));
			
			}  
			
		
			else if(opt.equalsIgnoreCase("D")) {
				ArrayList<CartBean> arcb=new ArrayList<CartBean>();
				 String s="";
					arcb=cs.viewAllCart();
			
			 for(CartBean o:arcb) {
				 s +=o.getExp();
				 s +="\n";
			 }
				
				int cartID=Integer.parseInt(JOptionPane.showInputDialog(s+ "Enter Cart ID to Delete Cart"));	
				ctBean.setCartID(cartID);
			
				JOptionPane.showMessageDialog(null,cs.deleteCart(ctBean)+" Cart Deleted");
				}
		
			else {
				JOptionPane.showMessageDialog(null,"WRONG Option");
			}	
		
		}	
		 
		 
		
		 
		 
		else if ((userId.equals("US-005")) && (auth)) {
			
			String opt=JOptionPane.showInputDialog("Want to Confirm Order? [Y/N] ");
				
			if(opt.equalsIgnoreCase("Y")) {
				
				ArrayList<CartBean> arcb=new ArrayList<CartBean>();
				 String str="";
					arcb=cs.viewAllCart();
			
			 for(CartBean o:arcb) {
				 str +=o.getExp();
				 str +="\n";
			 }
			 
			 ArrayList<StoreBean> ssb=new ArrayList<StoreBean>();
			 String st="";
				ssb=as.viewAllStore();
		
		 for(StoreBean o:ssb) {
			 st +=o.getExp();
			 st +="\n";
		 }
			
			//-	String orderID=JOptionPane.showInputDialog("Enter Order ID");
				ob.setOrderID("o"+ userId);
				String userid=userId;
				crBean.setUserID(userid);
				ob.setUserID(userid);
				Date orderDate = Date.valueOf(JOptionPane.showInputDialog("Enter Date in (yyyy-mm-dd) Format"));
				ob.setOrderdate(orderDate);
				String storeID=JOptionPane.showInputDialog(st+"Enter Store ID (S1-S10)");
				sb.setStoreID(storeID);
				ob.setStoreID(storeID);
				int cartID=Integer.parseInt(JOptionPane.showInputDialog(str+"Enter Cart Id (Num only)"));
				ctBean.setCartID(cartID);
				ob.setCartID(cartID);
//				String orderStatus=JOptionPane.showInputDialog("Your Order Status (1/0)");
				ob.setOrderStatus("Order Dispatched");
				String street=JOptionPane.showInputDialog("Enter Street");
				ob.setStreet(street);
				String city=JOptionPane.showInputDialog("Enter City");
				ob.setCity(city);
				String state=JOptionPane.showInputDialog("Enter State");
				ob.setState(state);
				String pincode=JOptionPane.showInputDialog("Enter Pincode");
				ob.setPincode(pincode);
				String mobileNo=JOptionPane.showInputDialog("Enter 10 digit Mobile No");
		        ob.setMobileNo(mobileNo);
				
		       
				
				
				
				JOptionPane.showMessageDialog(null,"Payment Process Begin....");
				String s=JOptionPane.showInputDialog("Credit Card Registerd? [Y/N]");
				
				if(s.equalsIgnoreCase("Y")) {
					while(true) {
					String ccNo=JOptionPane.showInputDialog("Enter Credit Card no");
					ccb.setCcNo(ccNo);
					
					boolean cAuth = CustomerDao.authCreditCard(ccb.getCcNo());
					
					if(cAuth) {
					CustomerDao.crCardPayment(ctBean,ccNo);
					JOptionPane.showMessageDialog(null,cs.confirmOrder(ob,ctBean,userID));
					break;
					}
					else {JOptionPane.showMessageDialog(null,"Wrong Credit card no"+"\n"+"Try Again.."); 
					continue;}
					
				  }
				}
				else if(s.equalsIgnoreCase("N")) {
					
					String ccNo=JOptionPane.showInputDialog("Enter Credit Card No (CCN_no)");
					ccb.setCcNo(ccNo);
					String validFrom=JOptionPane.showInputDialog("Valid From");
					ccb.setValidFrom(validFrom);
					String validTo=JOptionPane.showInputDialog("Valid To");
					ccb.setValidTo(validTo);
					int balance=Integer.parseInt(JOptionPane.showInputDialog("Enter CC Balance"));
					ccb.setBalance(balance);
					//String userid=JOptionPane.showInputDialog("Enter User ID");
					crBean.setUserID(userId);
					
					cs.addCard(ccb,userID);
					if(CustomerDao.crCardPayment(ctBean,ccNo))
					{
						JOptionPane.showMessageDialog(null,cs.confirmOrder(ob,ctBean,userID));
						JOptionPane.showMessageDialog(null,"Order Dispatched");
						
					}
					
				}
				
				
				
			}	
		 
			else if(opt.equalsIgnoreCase("N")) {
				JOptionPane.showMessageDialog(null,"Order can not Processed");
			}	
			
			
			
			
		}
		 
		
		else {
			JOptionPane.showMessageDialog(null,"WRONG User Credentials!!");
		}
	 }
					
			else {
				JOptionPane.showMessageDialog(null,"Customer Log-in Failed");
			}
			
	 }
	
	} 	
}

	
 class abc1
{
	 public static Connection con=DBUtil.con;
		public static PreparedStatement ps;
		public static ResultSet rs;
	 public static String abc(String fodid)
	 {
	String str1="";
	 try
	 		{
	 			ps=con.prepareStatement("select * from pos_tbl_food where FoodID=?");
	 			ps.setString(1, fodid);
	 			rs= ps.executeQuery();
	 			
	 			if(rs.next()) {
	 	           
	 	           str1=rs.getString(3);

	 	          
	 	            }
	 		}
	 		catch(SQLException sql)
	 		{
	 			System.out.println(sql);
	 		}
	 		return str1;
	 }
	 
	 public static int abc2(String fodid)
	 {
	int i=0;
	 try
	 		{
	 			ps=con.prepareStatement("select * from pos_tbl_food where FoodID=?");
	 			ps.setString(1, fodid);
	 			rs= ps.executeQuery();
	 			
	 			if(rs.next()) {
	 	           
	 	          i=rs.getInt(6);

	 	          
	 	            }
	 		}
	 		catch(SQLException sql)
	 		{
	 			System.out.println(sql);
	 		}
	 		return i;
	 }
}

	
