package com.shop.dto;

import com.shop.constant.ItemCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItemOrderDto {

    private Long id;

    private List<ItemOrderDto> itemOrderDtoList;


}