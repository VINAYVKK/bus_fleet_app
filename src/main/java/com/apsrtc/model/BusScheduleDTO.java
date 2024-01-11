package com.apsrtc.model;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BusScheduleDTO {

    @NotNull
    private Integer busId;

    @NotNull
    private Integer routeId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;
}
