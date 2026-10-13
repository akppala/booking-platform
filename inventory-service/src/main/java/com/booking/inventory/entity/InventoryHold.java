package com.booking.inventory.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.booking.inventory.util.HoldStatus;

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
@Table("inventory_hold")
public class InventoryHold extends BaseEntity {

    @Column("inventory_id")
    private UUID inventoryId;

    @Column("booking_reference")
    private String bookingReference;

    @Column("expires_at")
    private LocalDateTime expiresAt;

    @Column("status")
    private HoldStatus status;

}
