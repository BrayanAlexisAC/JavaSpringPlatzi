package com.training.spring.persistence.mappers;

import com.training.spring.domain.data.CategoryData;
import com.training.spring.persistence.entity.CategoryModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper {
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "available", target = "available")
    })
    CategoryModel toCategoryData(CategoryModel categoryModel);

    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    CategoryModel toCategoryModel(CategoryData categoryData);
}
