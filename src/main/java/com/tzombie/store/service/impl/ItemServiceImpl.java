package com.tzombie.store.service.impl;

import com.tzombie.store.domain.dto.ItemDTO;
import com.tzombie.store.domain.repository.ItemRepository;
import com.tzombie.store.service.ItemService;
import com.tzombie.store.service.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    private static final String CAN_NOT_BE_NULL = "Item id can not be null";
    private static final String MUST_BE_NULL = "Item id must be null";
    private static final String DONT_EXISTS = "Entity dont exists";
    private static final String MUST_BE_POSITIVE = "Price must be positive number";

    /**
     *
     * Метод получения информации о товаре по идентификатору
     *
     * @param id идентификатор товара
     * @return информация о товаре
     */
    @Override
    @Transactional(readOnly = true)
    public ItemDTO getItemById(Long id) {
        Validate.notNull(id, CAN_NOT_BE_NULL);

        return itemRepository.findById(id)
                .map(itemMapper::toDto)
                .orElse(null);
    }

    /**
     *
     * Метод добавления новой информации о товаре
     *
     * @param dto информация о товаре
     * @return информация о товаре
     */
    @Override
    @Transactional
    public ItemDTO createItem(ItemDTO dto) {
        Validate.isTrue(dto.getId() == null, MUST_BE_NULL);
        Validate.isTrue(Boolean.FALSE.equals(dto.getPrice() <= 0), MUST_BE_POSITIVE);

        var item = itemRepository.save(itemMapper.toEntity(dto));
        return itemMapper.toDto(item);
    }

    /**
     *
     * Метод получения списка информации о всех товарах
     *
     * @return Список информаций о товарах
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ItemDTO> getItems(Pageable pageable) {
        return itemRepository.findAll(pageable)
                .map(itemMapper::toDto);
    }

    /**
     *
     * Метод обновления информации о товаре
     *
     * @param dto информация о товаре
     * @return информация о товаре
     */
    @Override
    @Transactional
    public ItemDTO updateItem(ItemDTO dto) {
        Validate.notNull(dto.getId(), CAN_NOT_BE_NULL);
        Validate.isTrue(Boolean.FALSE.equals(dto.getPrice() <= 0), MUST_BE_POSITIVE);
        Validate.isTrue(itemRepository.existsById(dto.getId()), DONT_EXISTS);
        var item = itemRepository.save(itemMapper.toEntity(dto));
        return itemMapper.toDto(item);
    }

    /**
     *
     * Метод удаления информации о товаре по идентификатору
     *
     * @param id идентификатор товара
     */
    @Override
    @Transactional
    public void deleteItemById(Long id) {
        Validate.notNull(id, CAN_NOT_BE_NULL);
        Validate.isTrue(itemRepository.existsById(id), DONT_EXISTS);
        itemRepository.deleteById(id);
    }
}
