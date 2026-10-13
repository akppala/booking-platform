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
@Table("train_schedule")
public class TrainSchedule {

    @Id
    @Column("schedule_id")
    private UUID scheduleId;

    @Column("origin_station_id")
    private UUID originStationId;

    @Column("destination_station_id")
    private UUID destinationStationId;
}
