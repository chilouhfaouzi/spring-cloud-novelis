package io.novelis.smartroby.sample.service.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtoList);
    List <D> toDto(List<E> entityList);
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}
