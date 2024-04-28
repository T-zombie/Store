package com.tzombie.store.controller;

import com.tzombie.store.domain.dto.ItemDTO;
import com.tzombie.store.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Item")
@RequiredArgsConstructor
@RequestMapping("/api/item")
@SecurityRequirement(name = "JWT")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    @Operation(summary = "Get item by id")
    public ResponseEntity<ItemDTO> getById(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }

    @GetMapping
    @Operation(summary = "Get all items")
    public ResponseEntity<Page<ItemDTO>> getAll(@Parameter Pageable pageable) {
        var result = itemService.getItems(pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create item")
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.createItem(dto));
    }

    @PutMapping
    @Operation(summary = "Update item")
    public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.updateItem(dto));
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "Delete item")
    public ResponseEntity<Void> delete(@PathVariable Long itemId) {
        itemService.deleteItemById(itemId);
        return ResponseEntity.ok().build();
    }
}
