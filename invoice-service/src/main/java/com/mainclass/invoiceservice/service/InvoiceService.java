package com.mainclass.invoiceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mainclass.invoiceservice.model.Invoice;
import com.mainclass.invoiceservice.model.InvoiceDomainEventPublisher;
import com.mainclass.invoiceservice.model.InvoiceRepository;
import com.mainclass.servicemodel.invoice.api.events.InvoiceCreatedEvent;
import com.mainclass.servicemodel.invoice.api.events.InvoiceCreationFailedEvent;
import com.mainclass.servicemodel.order.api.info.ProductInfo;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import com.mainclass.servicemodel.invoice.api.events.InvoiceDomainEvent;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Collections.singletonList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Transactional
public class InvoiceService {

	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
    private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceDomainEventPublisher invoiceDomainEventPublisher;
	
	public Invoice createInvoice(String orderId, ProductInfo productInfo) {
		log.info("computing invoice for order - " + orderId);
		
		List<InvoiceDomainEvent> events = singletonList(new InvoiceCreatedEvent(orderId,productInfo.getComment()));
		ResultWithDomainEvents<Invoice,InvoiceDomainEvent> invoiceAndEvents = new ResultWithDomainEvents<>(new Invoice(orderId, productInfo.getComment()),events);
		
		Invoice invoice = invoiceRepository.save(new Invoice(orderId, productInfo.getComment()));
		invoiceDomainEventPublisher.publish(invoice, invoiceAndEvents.events);
		
		return invoice;
	}
		
	public void deleteInvoice(String orderId) {
		log.info("deleting invoice for order - " + orderId);
		
		List<InvoiceDomainEvent> events = singletonList(new InvoiceCreationFailedEvent(orderId));
		ResultWithDomainEvents<Invoice,InvoiceDomainEvent> invoiceAndEvents = new ResultWithDomainEvents<>(new Invoice(orderId),events);
		
		Invoice invoice = invoiceRepository.findByOrderId(orderId);
		
		if(invoice!=null) {
			invoiceRepository.delete(invoice);
			invoiceDomainEventPublisher.publish(invoice, invoiceAndEvents.events);
		}
		
		log.info("ERROR APPLICATION deleteInvoice - InvoiceService =>" + orderId);
	}
	
}
