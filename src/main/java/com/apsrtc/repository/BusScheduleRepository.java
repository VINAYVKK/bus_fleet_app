package com.apsrtc.repository;

import com.apsrtc.model.Bus;
import com.apsrtc.model.BusSchedule;
import com.apsrtc.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {
    List<BusSchedule> findByBusAndRouteAndEndTimeAfterAndStartTimeBefore(
            Bus bus, Route route, LocalDateTime startTime, LocalDateTime endTime);
    List<BusSchedule> findByBusAndRouteAndEndTimeAfterAndStartTimeBeforeAndScheduleIdNot(
            Bus bus, Route route, LocalDateTime startTime, LocalDateTime endTime, Integer scheduleId);

    List<BusSchedule> findByBusAndEndTimeAfterAndStartTimeBefore(
            Bus bus, LocalDateTime startTime, LocalDateTime endTime);

    List<BusSchedule> findByRouteAndEndTimeAfterAndStartTimeBefore(
            Route route, LocalDateTime startTime, LocalDateTime endTime);
}

