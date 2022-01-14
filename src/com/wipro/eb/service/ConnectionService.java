package com.wipro.eb.service;

import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;


public class ConnectionService {
	public boolean validate(int currentReading, int previousReading, String  type) throws InvalidReadingException, InvalidConnectionException {
		boolean result= false;
		boolean prevcurrentres= false;
		boolean typeres= false;
		if(currentReading < previousReading || previousReading<=0 || currentReading<=0) {
			throw  new InvalidReadingException();
		}
			else {
				prevcurrentres= true;
			}
			
			
		
		if(!(type.equals("Domestic")|| type.equals("Commercial"))) {
			throw new InvalidConnectionException();
		}
		else {
			typeres= true;
		}
		if(prevcurrentres&&typeres) {
			result = true;
		}
		
		
	return result;	
		
		
		
	}
	public float calculateBillAmt(int currentReading, int previousReading, String type) {
		
		boolean result=false;
		float endBill=0.0f;
		try {
			 result=validate(currentReading,previousReading,type);
		} catch (InvalidReadingException  e) {
			return -1;
		}
		catch(InvalidConnectionException ce) {
			return -2;
			
		}
		if(result == true) {
			if(type.equals("Domestic")) {
				Domestic domestic =new  Domestic(previousReading,currentReading,new float[] {2.3f,4.2f,5.5f} );
				endBill=domestic.computeBill();
		}
			
			else if(type.equals("Commercial")) {
				
				Commercial commercial = new Commercial(previousReading,currentReading,new float[] {5.2f,6.8f,8.3f}); 
				endBill=commercial.computeBill();
			}
			
		}
		return endBill;
	}
		
				
		
		
		
		
		
		
	
	
	public String generateBill(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException {
		float finalbill = 0.0f;
		finalbill= calculateBillAmt(currentReading,previousReading,type);
		
		if(finalbill == -1) {
			throw new InvalidReadingException();
			
		}
		else if (finalbill== -2) {
			throw new InvalidConnectionException();
		}
		
		else {
			
			return "Amount to be paid:"+ finalbill;
		}
		
		
	}


}
