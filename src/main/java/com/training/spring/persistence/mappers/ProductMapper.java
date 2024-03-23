package com.training.spring.persistence.mappers;

import com.training.spring.domain.data.ProductData;
import com.training.spring.persistence.entity.ProductModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "price", target = "price"),
        @Mapping(source = "stockQuantity", target = "stockQuantity"),
        @Mapping(source = "available", target = "available"),
        @Mapping(source = "category", target = "category")
    })
    ProductData toProductData(ProductModel productModel);
    List<ProductData> toProducts(List<ProductModel> lstProducts);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "idCategory", ignore = true),
        @Mapping(target = "barCode", ignore = true)
    })
    ProductModel toProductModel(ProductData productData);
}
