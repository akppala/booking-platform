package com.booking.inventory.dto;

import java.time.LocalDateTime;

import com.booking.inventory.util.ScheduleStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScheduleRequest {

    private String name;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ScheduleStatus status;
}
