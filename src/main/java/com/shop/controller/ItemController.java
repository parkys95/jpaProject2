package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.service.ItemService;
import com.shop.service.LikeService;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.nio.file.ClosedFileSystemException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.shop.entity.QItem.item;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemService;
    private final LikeService likeService;
    private final MemberService memberService;

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/index";
    }

    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {

        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }

        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {
        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/index";
    }

    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/itemMng";
    }

//    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
//    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loggedInUsername = authentication.getName();
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
//        Page<Item> items = itemService.getItemsCreatedByUser(loggedInUsername, itemSearchDto, pageable);
//
//        model.addAttribute("items", items);
//        model.addAttribute("itemSearchDto", itemSearchDto);
//        model.addAttribute("maxPage", 5);
//
//        return "item/itemMng";
//    }


    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId,Principal principal) {
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        itemService.updateView(itemId);

        Item item = new Item();
        Member member = memberService.getMemberInfo(principal.getName());
        item.setId(itemId);
        boolean islike = likeService.isNotAlreadyLike(member, item);
        log.info("~~~~~~~ isLike = " + islike);
        model.addAttribute("item", itemFormDto);
        model.addAttribute("heartCnt", islike ? 0 : 1);
        return "item/itemDtl";
    }
//    @GetMapping(value = "/item_pay")
//    public String itemDtl_pay(Model model) {
//        model.addAttribute("item");
//        return "item/itemDtl_pay";
//    }




//    @GetMapping("{itemId}")
//    public String findById(@PathVariable("itemId") Long itemId, Model model){
//        ItemFormDto itemFormDto = itemService.findById(itemId);
//        List<CommentDetailDTO> commentList = cs.findAll(itemId);
//
//        model.addAttribute("item",itemFormDto);
//        model.addAttribute("commentList",commentList);
//
//        bs.findLike(itemId,memberId);
//
//        int like = bs.findLike(boardId,memberId);
//        model.addAttribute("like",like);
//
//        return "/board/findById";
//    }
//    //좋아요


    @GetMapping("/like")
    @ResponseBody
    public Boolean like(long itemId, Principal principal) {
        Member member = memberService.getMemberInfo(principal.getName());

//        itemService.updateHeart(itemId,heart);
//        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
//        return itemFormDto.getHeart()+"";
        return likeService.addLike(member, itemId);
    }



//    @GetMapping("/likeCount")
//    @ResponseBody
//    public String likeCount(long itemId, int heartCount) {
//        itemService.updateHeartCount(itemId,heartCount);
//        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
//        return itemFormDto.getHeartCount()+"";
//
//    }


    @GetMapping(value = "/item/pay/{itemId}")
    public String payDown(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "pay/payDown";
    }


}