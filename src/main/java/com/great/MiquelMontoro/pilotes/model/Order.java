package com.great.MiquelMontoro.pilotes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.great.MiquelMontoro.pilotes.security.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "com.great.MiquelMontoro.pilotes.model.Order")
@Table(name="pilotes_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(length=100, nullable=false, name="delivery_address")
    private String deliveryAddress;

    @Column(nullable=false, name="number_of_pilotes")
    private Integer numberOfPilotes;

    @Column(name="order_total")
    private Float orderTotal;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    //bi-directional many-to-one association to WorkstationGroupModel
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
