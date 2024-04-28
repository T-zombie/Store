package com.tzombie.store.service;

import com.tzombie.store.domain.dto.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    ItemDTO getItemById(Long id);

    ItemDTO createItem(ItemDTO dto);

    Page<ItemDTO> getItems(Pageable pageable);

    ItemDTO updateItem(ItemDTO dto);

    void deleteItemById(Long id);
}
