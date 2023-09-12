package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reply")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member  member; // 작성자

}
