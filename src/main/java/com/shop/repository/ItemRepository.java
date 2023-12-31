    package com.shop.repository;

    import com.shop.constant.ItemCategory;
    import com.shop.dto.ItemDto;
    import com.shop.dto.ItemSearchDto;
    import com.shop.dto.MainItemDto;
    import com.shop.entity.Item;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import org.springframework.data.querydsl.QuerydslPredicateExecutor;

    public interface ItemRepository extends JpaRepository<Item, Long>,
            QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

        List<Item> findByItemNm(String itemNm);

        List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

        List<Item> findByPriceLessThan(Integer price);

        List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


        List<ItemDto> findByCategory(String category);

        List<Item> findByHashtag(String hashtag);

        List<Item> findByHashtagOrItemNm(String itemNm,String hashtag);

        @Query("select i from Item i where i.itemDetail like " +
                "%:itemDetail% order by i.price desc")
        List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


        @Query(value="select * from item i where i.hashtag like " +
                "%:hashtag% order by i.hashtag desc", nativeQuery = true)
        List<Item> findByHashtagByNative(@Param("hashtag") String hashtag);

        Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable,String hashtag);





//        Page<Item> findByCreatedByAndOtherCriteria(String loggedInUsername, ItemSearchDto itemSearchDto, Pageable pageable);
    }