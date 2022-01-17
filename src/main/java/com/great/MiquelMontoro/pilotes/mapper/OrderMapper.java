package com.great.MiquelMontoro.pilotes.mapper;

import com.great.MiquelMontoro.pilotes.model.Order;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface OrderMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTotal", ignore = true)
    Order mapOrder(com.great.MiquelMontoro.pilotes.dto.Order order);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    @Mapping(target = "orderDate", source = "creationDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "idUser", source = "user.id")
    com.great.MiquelMontoro.pilotes.dto.Order mapOrderInverse(Order order);

    @BeanMapping(ignoreByDefault = true, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<com.great.MiquelMontoro.pilotes.dto.Order> mapOrdersInverse(List<Order> orders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTotal", ignore = true)
    void updateRecord(com.great.MiquelMontoro.pilotes.dto.Order update, @MappingTarget Order oldRecord);
}

//    @Mapping(target = "numberOfPilotes", expression = "java(order.getNumberOfPilotes().getValue())")
//    @Mapping(target = "numberOfPilotes", ignore = true)
//    @Mapping(target = "type", source = "workstationTypeModel")
//    @Mapping(target = "workstationTraining", source = "trainingAssociationsModels")
//    @Mapping(target = "workstationConstraints", source = "workstationConstraintModels")
//    @Mapping(target = "workstationRestrictions", source = "restrictionAssociationsModels")
//    @Mapping(target = "jobTitleId", source = "jobTitleModel.id")
//@Mapping(source = "creationDate", target = "orderDate", dateFormat = "yyyy.MM.dd")
//da usare per il retrieval, vedi il tempo
