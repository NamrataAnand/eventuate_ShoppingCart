# mainclass
## Eventuate TRAM - SAGA Sample

- Java 8
- Docker
- Eventuate TRAM
- Maven
- Microservices Architecture
- CQRS
- Event Sourcing
- Saga Pattern
- Jenkins

### Sample Description

Order Service
- Receive a ProductInfo object and CustomerId.
- Create an order with state completed like false 
- Send a request to Customer Service to check if the customer exists. 
- Send a request to Invoice Service to generate an invoice order.
- If all the process is done successfully so it updates the order state to completed like true.

Customer Service
- Receive an orderId and customerId
- Check the Customer and return the result.

Invoice Service
- Receive a request to create an invoice.
- Create an invoice.
- Return an invoice if everything is ok.

Order View Service
- Receive all events generated by the other services
- Update a database
- Expose an API to get information about the services (i.e. Orders, Customers, Invoices)

>Note: If something wrong occurrs during the saga execution, the order is rejected  to compensate the process.

### Saga Definition - CreateOrderSaga
```sh
$ public CreateOrderSaga(OrderServiceProxy orderService, CustomerServiceProxy customerService, InvoiceServiceProxy invoiceService) {
		this.sagaDefinition =
				step()
					.withCompensation(orderService.reject, this::makeRejectOrderCommand)
				.step()
					.invokeParticipant(customerService.validateOrder, this::makeValidateOrderByCustomer)						
				.step()
					.invokeParticipant(invoiceService.requestInvoice, this::makeRequestInvoice)
					.onReply(InvoiceInfo.class, this::handleInvoiceReply)
					.withCompensation(invoiceService.cancelInvoice, this::makeCancelRequestInvoice)
				.step()
					.invokeParticipant(orderService.complete, this::makeCompleteOrderCommand)	
				.build();
```

### Building and Running

```sh
$ mvn clean package -s settings.xml docker:build
```

```sh
$ docker-compose up --abort-on-container-exit
```
### Swagger UI
Customer Service. (Create at least one customer)
```sh
http://localhost:5001/swagger-ui.html
```
Order Service. (Create an order to execute the saga)
```sh
http://localhost:5000/swagger-ui.html
```
Invoice Service. (Search your order invoices)
```sh
http://localhost:5002/swagger-ui.html
```
Order View Service. (Search everything)
```sh
http://localhost:5003/swagger-ui.html
```