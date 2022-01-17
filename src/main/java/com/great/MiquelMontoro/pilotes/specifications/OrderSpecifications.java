package com.great.MiquelMontoro.pilotes.specifications;

import com.great.MiquelMontoro.pilotes.model.Order;
import com.great.MiquelMontoro.pilotes.security.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;

public class OrderSpecifications {

    public static Specification<Order> hasId(Long id) {
        return (root, query, builder) -> {
            return (id != null) ? builder.equal(root.get("id"), id) : builder.conjunction();
        };
    }

    public static Specification<Order> deliveryAddressLike(String address){
        return (root, query, criteriaBuilder)
                -> (StringUtils.isNotEmpty(address)) ? criteriaBuilder.like(criteriaBuilder.upper(root.get("deliveryAddress")), "%"+address.toUpperCase()+"%") : criteriaBuilder.conjunction();
    }

    public static Specification<Order> hasCustomerId(Long id) {
        return (root, query, builder) -> {
            Path<User> u = root.get("user");
            return (id != null) ? builder.equal(u.get("id"), id) : builder.conjunction();
        };
    }

    public static Specification<Order> firstNameLike(String firstName) {
        return (root, query, builder) -> {
            if(!StringUtils.isEmpty(firstName)) {
                query.distinct(true);
                Join<Order, User> join = root.join("user");
                return builder.like(builder.upper(join.get("firstName")), "%"+firstName.toUpperCase()+"%");
            }
            else {
                return builder.conjunction();
            }
        };
    }

    public static Specification<Order> lastNameLike(String lastName) {
        return (root, query, builder) -> {
            if(!StringUtils.isEmpty(lastName)) {
                query.distinct(true);
                Join<Order, User> join = root.join("user");
                return builder.like(builder.upper(join.get("lastName")), "%"+lastName.toUpperCase()+"%");
            }
            else {
                return builder.conjunction();
            }
        };
    }

    public static Specification<Order> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }
}
