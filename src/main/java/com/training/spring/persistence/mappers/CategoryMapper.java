package com.training.spring.persistence.mappers;

import com.training.spring.domain.data.CategoryData;
import com.training.spring.persistence.entity.CategoryModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper {
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "available", target = "available"),
        @Mapping(source = "products", target = "products")
    })
    CategoryModel toCategoryData(CategoryModel categoryModel);

    @InheritInverseConfiguration
    CategoryModel toCategoryModel(CategoryData categoryData);
}
