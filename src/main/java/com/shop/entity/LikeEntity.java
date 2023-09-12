package com.shop.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="liketable")
@Getter @Setter
public class LikeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static LikeEntity toLikeEntity(Member member, Item item){
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setMember(member);
        likeEntity.setItem(item);

        return likeEntity;
    }



}
