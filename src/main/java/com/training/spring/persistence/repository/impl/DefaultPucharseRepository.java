package com.training.spring.persistence.repository.impl;

import com.training.spring.domain.data.PucharseData;
import com.training.spring.persistence.crud.OrderCrudRepository;
import com.training.spring.persistence.entity.OrderModel;
import com.training.spring.persistence.mappers.OrderMapper;
import com.training.spring.persistence.repository.PucharseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultPucharseRepository implements PucharseRepository {
    Logger log = LoggerFactory.getLogger(DefaultPucharseRepository.class);

    @Autowired
    private OrderCrudRepository crudRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<PucharseData> getAll() {
        return mapper.toPucharsesData((List<OrderModel>) crudRepository.findAll());
    }

    @Override
    public Optional<List<PucharseData>> getByClient(String idCustomer) {
        return crudRepository.findByIdCustomerOrderById(idCustomer).map(orderModels -> mapper.toPucharsesData(orderModels));
    }

    @Override
    public PucharseData save(PucharseData pucharseData) {
        try {
            OrderModel orderModel = mapper.toOrderModel(pucharseData);
            orderModel.getProducts().forEach(product -> product.setOrder(orderModel));
            return mapper.toPucharseData(crudRepository.save(orderModel));
        } catch (Exception e){
            log.error("Error to create order with client:{}, message:{}, stacktrace:{}", pucharseData.getClientId(), e.getMessage(), Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
