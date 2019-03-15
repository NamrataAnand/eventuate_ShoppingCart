package com.mainclass.servicemodel.order.api.command;

public class CompleteOrderCommand extends OrderCommand{

	public CompleteOrderCommand() {}
	
    public CompleteOrderCommand(String orderId) {
    	super(orderId);
    }
   
}