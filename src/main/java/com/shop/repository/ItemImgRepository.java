package com.shop.repository;

import com.shop.entity.CartItem;
import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);

    List<ItemImg> findByItemId(Long itemId);
<<<<<<< HEAD

    @Query("select i from ItemImg i where i.item.id = :id")
    List<ItemImg> getByItemId(Long id);

    @Modifying
    @Query("delete from ItemImg i where i.item.id = :itemId")
    void deleteItemImgs(Long itemId);
=======
>>>>>>> 8c2d8aadd5e751c34c54b0323a7f0f1909f21fd3
}