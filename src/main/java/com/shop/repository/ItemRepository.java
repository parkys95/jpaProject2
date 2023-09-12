    package com.shop.repository;

    import com.shop.dto.ItemDto;
    import com.shop.dto.ItemSearchDto;
    import com.shop.dto.MainItemDto;
    import com.shop.entity.Item;
<<<<<<< HEAD
<<<<<<< HEAD
    import com.shop.service.ItemService;
=======
>>>>>>> 4ef5c850f1cef9ff4ddc626d17f877efc3253ad2
=======
>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

    import java.util.List;

<<<<<<< HEAD
    import org.springframework.data.jpa.repository.Modifying;
=======
>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
=======
    import org.springframework.data.jpa.repository.Modifying;
>>>>>>> 174ade2fa8f7c515dfc0926c1ae368815580fd9e
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.querydsl.QuerydslPredicateExecutor;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

<<<<<<< HEAD
    import javax.transaction.Transactional;

    public interface ItemRepository extends JpaRepository<Item, Long>,
            QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

        List<Item> findByItemNm(String itemNm);

        List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

        List<Item> findByPriceLessThan(Integer price);

        List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


        List<ItemDto> findByCategory(String category);

=======
    public interface ItemRepository extends JpaRepository<Item, Long>,
            QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

        List<Item> findByItemNm(String itemNm);

        List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

        List<Item> findByPriceLessThan(Integer price);

        List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


        List<ItemDto> findByCategory(String category);

>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
        List<Item> findByHashtag(String hashtag);

        List<Item> findByHashtagOrItemNm(String itemNm,String hashtag);

        @Query("select i from Item i where i.itemDetail like " +
                "%:itemDetail% order by i.price desc")
        List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


        @Query(value="select * from item i where i.hashtag like " +
                "%:hashtag% order by i.hashtag desc", nativeQuery = true)
        List<Item> findByHashtagByNative(@Param("hashtag") String hashtag);

        Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable,String hashtag);
<<<<<<< HEAD



        @Modifying
        @Transactional
        @Query("DELETE FROM ItemImg WHERE item.id = :itemId")
        void deleteItemImagesByItemId(Long itemId);


<<<<<<< HEAD
//        Page<Item> findByCreatedByAndOtherCriteria(String loggedInUsername, ItemSearchDto itemSearchDto, Pageable pageable);
=======


>>>>>>> ae5dfb45d6fe8b4c40c1ed855fad6400cbac2ae8
=======


        @Modifying
        @Query("update Item i set i.view = i.view + 1 where i.id = :id")
        int updateView(Long id);

        @Query("SELECT i FROM Item i ORDER BY i.view DESC")
        Page<Item> findAllOrderByViewDesc(Pageable pageable);

        @Modifying
        @Query("update Item i set i.heart = :heart  where i.id = :itemId")
        int updateHeart(Long itemId,int heart);




>>>>>>> 174ade2fa8f7c515dfc0926c1ae368815580fd9e
    }