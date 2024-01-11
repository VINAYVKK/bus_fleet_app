package com.apsrtc.controller;

import java.util.List;

import com.apsrtc.model.RouteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apsrtc.exception.AdminException;
import com.apsrtc.exception.RouteException;
import com.apsrtc.model.Route;
import com.apsrtc.service.RouteService;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/apsrtc")
public class RouteController {
	
	@Autowired
	private RouteService routeService;

	@GetMapping("/route/all")
	public ResponseEntity<List<Route>> getAllRouteHandler() throws RouteException{
		
		List<Route> route= routeService.viewAllRoute();
		
		return new ResponseEntity<>(route,HttpStatus.OK);
	}

	@GetMapping("/route/{routeID}")
	public ResponseEntity<Route> getAllRouteByRouteIdHandler( @PathVariable Integer routeID) throws RouteException{
		
		Route route= routeService.viewRoute(routeID);
		
		return new ResponseEntity<>(route,HttpStatus.OK);
	}

}
