package com.great.MiquelMontoro.pilotes.security.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.great.MiquelMontoro.pilotes.model.Order;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.great.MiquelMontoro.pilotes.security.config.UserConfig.PHONE_NUMBER_PATTERN;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(unique=true, length = 50)
    private String username;

    @Column(length = 100)
//    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    @Column(length = 50, name="first_name")
    private String firstName;

    @Column(length = 50, name="last_name")
    private String lastName;

    @Column(name="phone_number")
    @Pattern(regexp = PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @Column(length = 50)
    private String authority;

    //bi-directional many-to-one association to WorkstationConstraint
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
