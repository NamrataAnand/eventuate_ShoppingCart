package com.mainclass.servicemodel.invoice.api.command;

import io.eventuate.tram.commands.common.Command;

public class CompensateInvoiceCommand implements Command{

	private String orderId;

	public CompensateInvoiceCommand() {}
	
	public CompensateInvoiceCommand(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
