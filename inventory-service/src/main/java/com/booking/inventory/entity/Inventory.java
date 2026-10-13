package com.booking.inventory.entity;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.inventory.util.InventoryStatus;
import com.booking.inventory.util.InventoryType;

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
@Table("inventory")
public class Inventory extends BaseEntity {

    @Column("schedule_id")
    private UUID scheduleId;

    @Column("parent_inventory_id")
    private UUID parentInventoryId;

    @Column("inventory_code")
    private String inventoryCode;

    @Column("display_name")
    private String displayName;

    @Column("inventory_type")
    private InventoryType inventoryType;

    @Column("capacity")
    private Integer capacity;

    @Column("booked")
    private Integer booked;

    @Column("status")
    private InventoryStatus status;
}