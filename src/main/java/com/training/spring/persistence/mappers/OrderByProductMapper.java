package com.training.spring.persistence.mappers;

import com.training.spring.domain.data.PucharseItemData;
import com.training.spring.persistence.entity.OrderByProductsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderByProductMapper {

    @Mappings({
        @Mapping(source = "composedId.idProduct", target = "productId"),
        @Mapping(source = "quantity", target = "quantity"),
        @Mapping(source = "totalPrice", target = "totalPrice"),
        @Mapping(source = "available", target = "available")
    })
    PucharseItemData toPucharseItemData(OrderByProductsModel orderByProductsModel);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "order", ignore = true),
        @Mapping(target = "product", ignore = true),
        @Mapping(target = "composedId.idOrder", ignore = true)
    })
    OrderByProductsModel toOrderByProductModel(PucharseItemData pucharseItemData);
}
