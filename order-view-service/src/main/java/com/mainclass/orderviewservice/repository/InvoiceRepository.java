package com.mainclass.orderviewservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mainclass.orderviewservice.model.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String>{

}
