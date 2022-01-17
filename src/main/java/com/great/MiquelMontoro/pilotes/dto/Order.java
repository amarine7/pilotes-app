package com.great.MiquelMontoro.pilotes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-02T15:00:03.964Z[GMT]")

@Builder
public class Order   {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("deliveryAddress")
    private String deliveryAddress = null;

    @JsonProperty("numberOfPilotes")
    private Integer numberOfPilotes = null;

    @JsonProperty("orderTotal")
    private Float orderTotal = null;

    @JsonProperty("orderDate")
    private String orderDate = null;

    @JsonProperty("idUser")
    private Long idUser = null;

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    @Schema(description = "")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order deliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    /**
     * Get deliveryAddress
     * @return deliveryAddress
     **/
    @Schema(required = true, description = "")
    @NotNull

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Order numberOfPilotes(Integer numberOfPilotes) {
        this.numberOfPilotes = numberOfPilotes;
        return this;
    }

    /**
     * Get numberOfPilotes
     * @return numberOfPilotes
     **/
    @Schema(required = true, description = "")
    @NotNull

    public Integer getNumberOfPilotes() {
        return numberOfPilotes;
    }

    public void setNumberOfPilotes(Integer numberOfPilotes) {
        this.numberOfPilotes = numberOfPilotes;
    }

    public Order orderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
        return this;
    }

    /**
     * Get orderTotal
     * @return orderTotal
     **/
    @Schema(description = "")

    @Valid
    public Float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Order orderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * Get orderDate
     * @return orderDate
     **/
    @Schema(description = "")

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Order idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    /**
     * Get idUser
     * @return idUser
     **/
    @Schema(description = "")

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) &&
                Objects.equals(this.deliveryAddress, order.deliveryAddress) &&
                Objects.equals(this.numberOfPilotes, order.numberOfPilotes) &&
                Objects.equals(this.orderTotal, order.orderTotal) &&
                Objects.equals(this.orderDate, order.orderDate) &&
                Objects.equals(this.idUser, order.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryAddress, numberOfPilotes, orderTotal, orderDate, idUser);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Order {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    deliveryAddress: ").append(toIndentedString(deliveryAddress)).append("\n");
        sb.append("    numberOfPilotes: ").append(toIndentedString(numberOfPilotes)).append("\n");
        sb.append("    orderTotal: ").append(toIndentedString(orderTotal)).append("\n");
        sb.append("    orderDate: ").append(toIndentedString(orderDate)).append("\n");
        sb.append("    idUser: ").append(toIndentedString(idUser)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}