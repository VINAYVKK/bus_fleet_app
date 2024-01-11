package com.apsrtc.service;

import java.util.List;

import com.apsrtc.exception.RouteException;
import com.apsrtc.model.Route;

public interface RouteService {
	public List<Route> viewAllRoute() throws RouteException;
	public Route viewRoute(int routeId) throws RouteException;
	
	
}
