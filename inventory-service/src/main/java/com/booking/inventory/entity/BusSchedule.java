package com.booking.inventory.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("bus_schedule")
public class BusSchedule {

    @Id
    @Column("schedule_id")
    private UUID scheduleId;

    @Column("route_id")
    private UUID routeId;

    @Column("departure_stop_id")
    private UUID departureStopId;

    @Column("arrival_stop_id")
    private UUID arrivalStopId;
}