package com.training.spring.persistence.mappers;

import com.training.spring.domain.data.PucharseData;
import com.training.spring.persistence.entity.OrderModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderByProductMapper.class})
public interface OrderMapper {
    @Mappings({
        @Mapping(source = "id", target = "pucharseId"),
        @Mapping(source = "idCustomer", target = "clientId"),
        @Mapping(source = "createdDate", target = "createDate"),
        @Mapping(source = "paymentMethod", target = "paymentMethod"),
        @Mapping(source = "comment", target = "comment"),
        @Mapping(source = "products", target = "items"),
    })
    PucharseData toPucharseData(OrderModel orderModel);

    @InheritInverseConfiguration
    List<PucharseData> toPucharsesData(List<OrderModel> lstOrdersModel);

    @InheritInverseConfiguration
    @Mapping(target = "customer", ignore = true)
    OrderModel toOrderModel(PucharseData pucharseData);
}
