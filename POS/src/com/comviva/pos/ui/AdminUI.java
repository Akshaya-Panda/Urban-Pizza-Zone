package com.comviva.pos.ui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.comviva.pos.bean.CartBean;
import com.comviva.pos.bean.CredentialsBean;
import com.comviva.pos.bean.FoodBean;
import com.comviva.pos.bean.StoreBean;
import com.comviva.pos.dao.AdministratorDao;
import com.comviva.pos.service.Administrator;
import com.comviva.pos.service.Customer;

public class AdminUI {
	
	Administrator as = new Administrator();
	Customer cs = new Customer();
	StoreBean sb=new StoreBean();
	FoodBean fb=new FoodBean();
	CartBean ctBean=new CartBean();
	CredentialsBean cb=new CredentialsBean();
	
	
	public void ValidateAdmin() {
		String userId=JOptionPane.showInputDialog("Enter ADMIN UserID");
		cb.setUserID(userId);
		String password=JOptionPane.showInputDialog("Enter ADMIN Password");
		cb.setPassword(password);
		cb.setStr(cb.getUserID()+cb.getPassword());
		
		boolean auth = AdministratorDao.authenticate(cb);
		
		if ((userId.equals("AD-001")) && (auth)) {
			
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(Please ADD Store)");
			String storeID=JOptionPane.showInputDialog("Enter Store ID");
			sb.setStoreID(storeID);
			String name=JOptionPane.showInputDialog("Enter Store Name");
			sb.setName(name);
			String street=JOptionPane.showInputDialog("Enter Street Name");
			sb.setStreet(street);
			String mobileNo=JOptionPane.showInputDialog("Enter Store Mobile No");
			sb.setMobileNo(mobileNo);
			String city=JOptionPane.showInputDialog("Enter Store City");
			sb.setCity(city);
			String state=JOptionPane.showInputDialog("Enter Store State");
			sb.setState(state);
			String pincode=JOptionPane.showInputDialog("Enter Store Pincode");
			sb.setPincode(pincode);
			
			JOptionPane.showMessageDialog(null,as.addStore(sb));
		}
	

		
		else if ((userId.equals("AD-002")) && (auth)) {
			
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(Now DELETE a Store)");
			
			String storeID=JOptionPane.showInputDialog("Enter Store ID to DELETE details");	
			sb.setStoreID(storeID);
			
			JOptionPane.showMessageDialog(null,as.removeStore(sb)+" Store Deleted");
		}
		
		else if ((userId.equals("AD-003")) && (auth)) {
			
            JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(All Store Details are...)");
			
			ArrayList<StoreBean> asb=new ArrayList<StoreBean>();
			String str="";
			
			asb=as.viewAllStore();
			
			for (StoreBean sb : asb) {
				str+=sb.getExp()+"\n";
			}
			
			JOptionPane.showMessageDialog(null, str);
			
		}
		
		else if ((userId.equals("AD-004")) && (auth)) {
			
            JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(UPDATE a Store Detail)");
			
			String storeID=JOptionPane.showInputDialog("Enter Store ID to Update details");
			sb.setStoreID(storeID);
			String name=JOptionPane.showInputDialog("Enter New Store Name");
			sb.setName(name);
			String street=JOptionPane.showInputDialog("Enter New Street Name");
			sb.setStreet(street);
			String mobileNo=JOptionPane.showInputDialog("Enter New Store Mobile No");
			sb.setMobileNo(mobileNo);
			String city=JOptionPane.showInputDialog("Enter New Store City");
			sb.setCity(city);
			String state=JOptionPane.showInputDialog("Enter New Store State");
			sb.setState(state);
			String pincode=JOptionPane.showInputDialog("Enter New Store Pincode");
			sb.setPincode(pincode);
			
			JOptionPane.showMessageDialog(null,as.modifyStore(sb));		
			
				}
		
		else if ((userId.equals("AD-005")) && (auth)) {
			
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(Now ADD a Food item)");
			
			String foodID=JOptionPane.showInputDialog("Enter Food ID");
			fb.setFoodID(foodID);
			String name=JOptionPane.showInputDialog("Enter Food Name");
			fb.setName(name);
			String ftype=JOptionPane.showInputDialog("Enter Food Type");
			fb.setType(ftype);
			String fsize=JOptionPane.showInputDialog("Enter Food Size");
			fb.setFoodSize(fsize);
			int quantity=Integer.parseInt(JOptionPane.showInputDialog("Enter Food Quantity"));
			fb.setQuantity(quantity);
			int price=Integer.parseInt(JOptionPane.showInputDialog("Enter Food Price"));
			fb.setPrice(price);
			
			JOptionPane.showMessageDialog(null,as.addFood(fb));
		}
		
		else if ((userId.equals("AD-006")) && (auth)) {
			
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(Now DELETE a Food item)");
			
			String foodID=JOptionPane.showInputDialog("Enter Food ID to DELETE details");	
			fb.setFoodID(foodID);
			
			JOptionPane.showMessageDialog(null,as.removeFood(fb)+" Food Item Deleted");
		}
		
			
		
		else if ((userId.equals("AD-007")) && (auth)) {
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(Choose a Option to view Details)");		
			String opt=JOptionPane.showInputDialog("Food Details(FD) / Customer Cart Details(CD)");
			
			if(opt.equalsIgnoreCase("FD")) {					
				JOptionPane.showMessageDialog(null,"Showing All Food Details...");
				
				ArrayList<FoodBean> afb=new ArrayList<FoodBean>();
				String str="";
				
				afb=as.viewAllFood();
				
				for (FoodBean fb : afb) {
					str+=fb.getExp()+"\n";
				}
			
				
				JOptionPane.showMessageDialog(null, str);				
			}
			
			else if(opt.equalsIgnoreCase("CD")) {
				JOptionPane.showMessageDialog(null,"Showing All Cart Details of Customer...");
				
				ArrayList<CartBean> arcb=new ArrayList<CartBean>();
				String str="";
				
				arcb=cs.viewAllCart();

				for (CartBean cb : arcb) {
					str+=cb.getExp()+"\n";
				}
				
				JOptionPane.showMessageDialog(null, str);		
			}
		}
			
			else if ((userId.equals("AD-008")) && (auth)) {
			
			JOptionPane.showMessageDialog(null,"Admin Log-in SUCCESSFUL"+"\n"+"(UPDATE Food Details)");
			
			String foodID=JOptionPane.showInputDialog("Enter Food ID to Update Details");
			fb.setFoodID(foodID);
			String name=JOptionPane.showInputDialog("Enter New Food Name");
			fb.setName(name);
			String ftype=JOptionPane.showInputDialog("Enter New Food Type");
			fb.setType(ftype);
			String fsize=JOptionPane.showInputDialog("Enter New Food Size");
			fb.setFoodSize(fsize);
			int quantity=Integer.parseInt(JOptionPane.showInputDialog("Enter New Food Quantity"));
			fb.setQuantity(quantity);
			int price=Integer.parseInt(JOptionPane.showInputDialog("Enter New Food Price"));
			fb.setPrice(price);
			
			JOptionPane.showMessageDialog(null,as.modifyFood(fb));
		}
		
		
		

}
}
