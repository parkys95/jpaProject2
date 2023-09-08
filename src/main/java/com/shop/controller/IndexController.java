package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ItemService itemService;

    @GetMapping(value = "/index")
    public String index(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){


        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 50);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable,"");


        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "index";
    }


    @GetMapping(value = "/icon/{category}")
    public String icon(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model, @PathVariable("category") String catrgory){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 30);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable, catrgory);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "/category/index_icon";
    }

    @GetMapping(value = "/illust/{category}")
    public String illust(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model,  @PathVariable("category") String category){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 30);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable, category);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "/category/index_illust";
    }

    @GetMapping(value = "/photo/{category}")
    public String photo(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model, @PathVariable("category") String category){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 30);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable, category);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "/category/index_photo";
    }





}