package com.mainclass.orderviewservice.dao;

import java.util.List;

import com.mainclass.orderviewservice.model.Invoice;

public interface InvoiceService {

	public Invoice createOrderInvoice(String invoiceId, String orderId, String invoiceComment);
	public void creationOrderInvoiceFailed(String invoiceId);
	public Invoice findInvoice(String invoiceId);
	public List<Invoice> findAll();
}
