package com.comviva.pos.ui;

import javax.swing.JOptionPane;

public class Main 
{
	public static void main(String[] args) {
		
		CustomerUI cui=new CustomerUI();
		AdminUI aui=new AdminUI();
		
		JOptionPane.showMessageDialog(null,"WELCOME TO URBAN PIZZA-ZONE");
		String opt=JOptionPane.showInputDialog("Enter USER Type- [ Admin(A) / Customer(C) ]");
		
	 while(true) {
			
		try {	
			
		if(opt.equalsIgnoreCase("A")) {
			aui.ValidateAdmin();
			String abc=JOptionPane.showInputDialog("Do You want to EXIT ? (Y/N)");
			if(abc.equalsIgnoreCase("Y"))
				System.exit(0);
			else
				continue;
		}
		
	else if(opt.equalsIgnoreCase("C")) {
			cui.ValidateCustomer();
			String abc=JOptionPane.showInputDialog("Do You want to EXIT ? (Y/N)");
			if(abc.equalsIgnoreCase("Y"))
				System.exit(0);
			else
				continue;
		}
		else {
			JOptionPane.showMessageDialog(null,"Invalid Entry!! Please Enter Valid Value");
			System.exit(0);
		}
	  }
		catch (Exception e) {
		  		JOptionPane.showMessageDialog(null,e.getMessage());		  
	        }
		}	
	}
}


