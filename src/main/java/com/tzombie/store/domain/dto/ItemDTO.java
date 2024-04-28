package com.tzombie.store.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzombie.store.domain.enumeration.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private ItemCategory category;
}
