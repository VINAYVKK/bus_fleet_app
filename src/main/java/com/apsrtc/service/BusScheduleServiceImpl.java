package com.apsrtc.service;

import com.apsrtc.model.BusScheduleDTO;
import com.apsrtc.model.Bus;
import com.apsrtc.model.BusSchedule;
import com.apsrtc.model.Route;
import com.apsrtc.repository.BusRepository;
import com.apsrtc.repository.BusScheduleRepository;
import com.apsrtc.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

    private final BusScheduleRepository busScheduleRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public BusScheduleServiceImpl(BusScheduleRepository busScheduleRepository,
                                  BusRepository busRepository,
                                  RouteRepository routeRepository) {
        this.busScheduleRepository = busScheduleRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
    }

    public BusSchedule addBusSchedule(BusScheduleDTO scheduleRequest) {
        Bus bus = busRepository.findById(scheduleRequest.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + scheduleRequest.getBusId()));

        Route route = routeRepository.findById(scheduleRequest.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with ID: " + scheduleRequest.getRouteId()));

        LocalDateTime startTime = scheduleRequest.getStartTime();
        LocalDateTime endTime = scheduleRequest.getEndTime();

        // Check for overlapping schedules
        List<BusSchedule> overlappingBusSchedules = busScheduleRepository
                .findByBusAndEndTimeAfterAndStartTimeBefore(bus, startTime, endTime);

        List<BusSchedule> overlappingRouteSchedules = busScheduleRepository
                .findByRouteAndEndTimeAfterAndStartTimeBefore(route, startTime, endTime);

        if (!overlappingBusSchedules.isEmpty()) {
            throw new RuntimeException("Bus schedule overlaps with its own existing schedules.");
        }

        if (!overlappingRouteSchedules.isEmpty()) {
            throw new RuntimeException("Bus schedule overlaps with existing route schedules.");
        }

        // Create and save the new schedule
        BusSchedule newSchedule = new BusSchedule();
        newSchedule.setBus(bus);
        newSchedule.setRoute(route);
        newSchedule.setStartTime(startTime);
        newSchedule.setEndTime(endTime);

        busScheduleRepository.save(newSchedule);
        return newSchedule;
    }

    public BusSchedule updateBusSchedule(Integer scheduleId, BusScheduleDTO updatedSchedule) {
        BusSchedule schedule = busScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Bus schedule not found with ID: " + scheduleId));

        Bus bus = busRepository.findById(updatedSchedule.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + updatedSchedule.getBusId()));

        Route route = routeRepository.findById(updatedSchedule.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with ID: " + updatedSchedule.getRouteId()));

        LocalDateTime startTime = updatedSchedule.getStartTime();
        LocalDateTime endTime = updatedSchedule.getEndTime();

        // Check for overlapping schedules excluding the current schedule being updated
        List<BusSchedule> overlappingSchedules = busScheduleRepository
                .findByBusAndRouteAndEndTimeAfterAndStartTimeBeforeAndScheduleIdNot(
                        bus, route, startTime, endTime, scheduleId);

        if (!overlappingSchedules.isEmpty()) {
            throw new RuntimeException("Updated bus schedule overlaps with existing schedules.");
        }

        // Update the schedule
        schedule.setBus(bus);
        schedule.setRoute(route);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);

        busScheduleRepository.save(schedule);
        return schedule;
    }

    public BusSchedule deleteBusSchedule(Integer scheduleId) {
        busScheduleRepository.deleteById(scheduleId);
        return null;
    }
}
