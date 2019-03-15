package com.mainclass.orderservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mainclass.orderservice.saga.createorder.CreateOrderSaga;
import com.mainclass.orderservice.saga.createorder.CreateOrderSagaData;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;

@Configuration
@Import({TramEventsPublisherConfiguration.class, SagaOrchestratorConfiguration.class})
public class OrderServiceConfiguration {
	
	@Bean
	public SagaManager<CreateOrderSagaData> createOrderSagaManager(CreateOrderSaga saga) {
	    return new SagaManagerImpl<>(saga);
	}
	
	@Bean
	public ChannelMapping channelMapping() {
		return DefaultChannelMapping.builder().build();
	}
	
}
