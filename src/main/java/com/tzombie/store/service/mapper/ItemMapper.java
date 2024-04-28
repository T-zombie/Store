package com.tzombie.store.service.mapper;

import com.tzombie.store.domain.dto.ItemDTO;
import com.tzombie.store.domain.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ItemMapper {

    Item toEntity(ItemDTO dto);

    ItemDTO toDto(Item entity);
}
