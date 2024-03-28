package com.training.spring.persistence.crud;

import com.training.spring.persistence.entity.OrderModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderCrudRepository extends CrudRepository<OrderModel, Integer> {
    Optional<List<OrderModel>> findByIdCustomerOrderById(String idCustomer);
}
