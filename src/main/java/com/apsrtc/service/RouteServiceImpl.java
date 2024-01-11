package com.apsrtc.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apsrtc.exception.RouteException;
import com.apsrtc.model.Route;
import com.apsrtc.model.RouteDTO;
import com.apsrtc.repository.RouteRepository;
@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepository  routerepository;

	@Autowired
	private BusScheduleService busScheduleService;

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		List<Route> routes = routerepository.findAll();

		if (routes.isEmpty()) {
			throw new RouteException("No route available");
		} else {
			return routes;
		}
	}
	@Override
	public Route viewRoute(int routeId) throws RouteException {
		 Optional<Route> opt=routerepository.findById(routeId);

		 return opt.orElseThrow(()->new RouteException("There is no route present of this  routeId :" + routeId));
	}

}
