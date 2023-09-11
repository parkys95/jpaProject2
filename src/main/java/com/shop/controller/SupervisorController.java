package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MemberSearchDto;
import com.shop.dto.MemberUpdateFormDto;
import com.shop.entity.*;
import com.shop.repository.*;
import com.shop.service.ItemService;
import com.shop.service.MailService;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/supervisor")
@Controller
@RequiredArgsConstructor
public class SupervisorController {

    private final MemberRepository memberRepository;
    private final MailService mailService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final ItemService itemService;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @GetMapping("/page")
    public String supervisorPage() {
        // 관리자 페이지의 뷰 이름을 반환합니다.
        return "/supervisor/supervisorPage";
    }

    @GetMapping(value = {"/items", "/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "/supervisor/svItemMng";
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        try {
            // 1. 상품과 관련된 주문 항목을 삭제합니다.
            List<OrderItem> orderItems = orderItemRepository.findByItemId(itemId);
            orderItemRepository.deleteAll(orderItems);

            // 2. 상품과 관련된 cart_item 레코드를 삭제합니다.
            List<CartItem> cartItems = cartItemRepository.findByItemId(itemId);
            cartItemRepository.deleteAll(cartItems);

            // 3. 상품을 삭제합니다.
            itemService.deleteItem(itemId);

            return new ResponseEntity<String>("상품이 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("상품 삭제 중 오류가 발생했습니다.");
        }
    }




//    @GetMapping(value = {"/members", "/members/{page}"})
//    public String membersManage(MemberSearchDto memberSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
//        Page<Member> members = memberService.getMemberPage(memberSearchDto, pageable);
//
//        model.addAttribute("members", members);
//        model.addAttribute("memberSearchDto", memberSearchDto);
//        model.addAttribute("maxPage", 5);
//
//        return "supervisor/svMembersMng";
//    }

    @GetMapping("/members")
    public String listMembers(Model model,
                              @RequestParam(defaultValue = "") String searchType,
                              @RequestParam(defaultValue = "") String keyword,
                              @PageableDefault(size = 5, sort = "id",
                                      direction = Sort.Direction.DESC) Pageable pageable) {

        if ("name".equals(searchType)) {
            model.addAttribute("members",
                    memberService.getMembersByName(keyword, pageable));
        } else if ("email".equals(searchType)) {
            model.addAttribute("members",
                    memberService.getMembersByEmail(keyword, pageable));
        } else {
            model.addAttribute("members",
                    memberService.getAllMembers(pageable));
        }

        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "supervisor/svMembersMng"; // 렌더링할 Thymeleaf 템플릿 경로
    }



    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable String memberId) {
        try {
            // 1. 회원과 관련된 주문을 삭제합니다.
            List<Order> orders = orderRepository.findByMemberId(memberId);
            orderRepository.deleteAll(orders);

            // 2. 회원과 관련된 카트 아이템을 삭제합니다.
            List<CartItem> cartItems = cartItemRepository.findByCartMemberId(memberId);
            cartItemRepository.deleteAll(cartItems);

            // 3. 회원과 관련된 카트를 삭제합니다.
            Cart cart = cartRepository.findByMemberId(Long.valueOf(memberId));
            if (cart != null) {
                cartRepository.delete(cart);
            }

            // 4. 회원을 삭제합니다.
            memberService.deleteMember(memberId);

            return new ResponseEntity<String>("사용자 아이디가 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("아이디 삭제 중 오류가 발생했습니다.");
        }
    }


}