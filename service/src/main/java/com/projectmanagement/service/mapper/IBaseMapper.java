package com.projectmanagement.service.mapper;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBaseMapper {
    default <T, D> D map(T source, Class<D> targetClass) {
        if (source == null) return null;
        return new org.modelmapper.ModelMapper().map(source, targetClass);
    }

    default <S, D> List<D> mapList(List<S> sourceList, Class<D> targetClass) {
        return sourceList.stream()
                .map(s -> map(s, targetClass))
                .toList();
    }
}
