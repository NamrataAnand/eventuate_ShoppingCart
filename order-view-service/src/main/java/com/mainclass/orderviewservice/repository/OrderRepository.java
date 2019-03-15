package com.mainclass.orderviewservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mainclass.orderviewservice.model.Order;

public interface OrderRepository extends MongoRepository<Order,String>{

}
