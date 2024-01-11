package com.apsrtc.service;

import com.apsrtc.exception.AdminException;
import com.apsrtc.exception.BusException;
import com.apsrtc.model.Bus;
import com.apsrtc.model.CurrentAdminSession;
import com.apsrtc.model.Route;
import com.apsrtc.repository.BusRepository;
import com.apsrtc.repository.CurrentAdminSessionRepository;
import com.apsrtc.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository busRepo;
    @Autowired(required = false)
    private RouteRepository routeRepo;
    @Autowired
    private CurrentAdminSessionRepository currAdminRepo;
    //admin access methods
    @Override
    public Bus addBus(Bus bus, String key) throws BusException , AdminException {
        CurrentAdminSession admin = currAdminRepo.findByaid(key);
        if(admin==null){
            throw new AdminException("Key is not valid! Please provide a valid key.");
        }
        
        //saving bus
        return busRepo.save(bus);
    }

    @Override
    public Bus updateBus(Bus bus, String key) throws BusException , AdminException{
        CurrentAdminSession admin = currAdminRepo.findByaid(key);
        if(admin==null){
            throw new AdminException("Key is not valid! Please provide a valid key.");
        }
        Optional<Bus> bus1 = busRepo.findById(bus.getBusId());
        if(!bus1.isPresent()){
            throw new BusException("Bus doesn't exist with busId: "+ bus.getBusId());
        }
        return busRepo.save(bus);
    }

    @Override
    public Bus deleteBus(Integer busId, String key) throws BusException, AdminException {
        CurrentAdminSession admin = currAdminRepo.findByaid(key);
        if(admin==null){
            throw new AdminException("Key is not valid! Please provide a valid key.");
        }

        Optional<Bus> bus = busRepo.findById(busId);

        if(bus.isPresent()){
            Bus existingBus = bus.get();
            busRepo.delete(existingBus);
            return existingBus;

        } else throw  new BusException("Bus not found with busId: "+busId);

    }


    //admin + user access methods
    @Override
    public List<Bus> viewAllBuses() throws BusException {
        List<Bus> busList = busRepo.findAll();
        if(busList.isEmpty()){
            throw new BusException("No bus found at this moment. Try again later!");
        }
        return busList;
    }

    @Override
    public Bus viewBus(Integer busId) throws BusException {
        Optional<Bus> existingBus = busRepo.findById(busId);
        if(!existingBus.isPresent()){
            throw new BusException("No bus exist with this busId: "+ busId);
        }
        return existingBus.get();
    }

    @Override
    public List<Bus> viewBusByBusType(String busType) throws BusException {
        List<Bus> busListType = busRepo.findByBusType(busType);
        if(busListType.isEmpty()){
            throw new BusException("There are no buses with bus type: "+ busType);
        }
        return busListType;
    }

}
