package com.lukhol.chat.common;

public class CalculatorImpl implements Calculator{
	
	public CalculatorImpl(){
		System.out.println("New calc create.");
	}
	
	public int add(int i1, int i2) {
        return i1 + i2;
	}
	
	public String test(String test){
		return test + " - success!";
	}
}
