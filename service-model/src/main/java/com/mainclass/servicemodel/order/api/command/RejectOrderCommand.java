package com.mainclass.servicemodel.order.api.command;

public class RejectOrderCommand extends OrderCommand{

	private RejectOrderCommand() {
	}
	
	public RejectOrderCommand(String orderId) {
		super(orderId);
	}
}
