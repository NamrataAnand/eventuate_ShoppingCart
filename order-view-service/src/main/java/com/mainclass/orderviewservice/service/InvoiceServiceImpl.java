package com.mainclass.orderviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderviewservice.dao.InvoiceService;
import com.mainclass.orderviewservice.model.Invoice;
import com.mainclass.orderviewservice.repository.InvoiceRepository;

import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class InvoiceServiceImpl implements InvoiceService{

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public Invoice createOrderInvoice(String invoiceId, String orderId, String invoiceComment) {
		log.info("createOrderInvoice - InvoiceServiceImpl");
		Invoice invoice = new Invoice(invoiceId, orderId, invoiceComment);
		return invoiceRepository.save(invoice);
	}

	@Override
	public void creationOrderInvoiceFailed(String invoiceId) {
		log.info("creationOrderInvoiceFailed - InvoiceServiceImpl");
		Invoice invoice = findInvoice(invoiceId);
		
		if(invoice!=null) {
			invoiceRepository.delete(invoice);
		}else {
			log.info("unexpected ERROR");
		}
			
	}

	@Override
	public Invoice findInvoice(String invoiceId) {		
		try {
			Invoice invoice = invoiceRepository.findById(invoiceId).get();
			return invoice;
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

}
