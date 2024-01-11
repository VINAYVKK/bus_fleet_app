package com.apsrtc.service;

import com.apsrtc.exception.AdminException;
import com.apsrtc.exception.BusException;
import com.apsrtc.model.Bus;
import com.apsrtc.model.BusSchedule;
import com.apsrtc.model.BusScheduleDTO;

import java.util.List;

public interface BusScheduleService {
    public BusSchedule addBusSchedule(BusScheduleDTO scheduleRequest) throws BusException;

    public BusSchedule updateBusSchedule(Integer scheduleId, BusScheduleDTO updatedSchedule) throws BusException;

    public BusSchedule deleteBusSchedule(Integer scheduleId)  throws BusException;



}