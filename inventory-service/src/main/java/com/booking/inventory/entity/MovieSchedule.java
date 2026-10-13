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
@Table("movie_schedule")
public class MovieSchedule {

    @Id
    @Column("schedule_id")
    private UUID scheduleId;

    @Column("venue_id")
    private UUID venueId;

    @Column("screen_id")
    private UUID screenId;
}