package com.booking.inventory.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.booking.inventory.util.PricingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("pricing")
public class Pricing extends BaseEntity {

    @Column("schedule_id")
    private UUID scheduleId;

    @Column("inventory_id")
    private UUID inventoryId;

    @Column("currency")
    private String currency;

    @Column("base_price")
    private BigDecimal basePrice;

    @Column("tax")
    private BigDecimal tax;

    @Column("discount")
    private BigDecimal discount;

    @Column("final_price")
    private BigDecimal finalPrice;

    @Column("status")
    private PricingStatus status;

}
