package com.apsrtc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO implements Serializable {
    private Integer routeID;
    private String routeFrom;
    private String routeTo;
    private Integer distance;
    private List<BusScheduleDTO> schedules;

//    public RouteDTO(Route route) {
//        this.routeID = route.getRouteID();
//        this.routeFrom = route.getRouteFrom();
//        this.routeTo = route.getRouteTo();
//        this.distance = route.getDistance();
//        this.schedules = route.getSchedules()
//                .stream()
//                .map(BusScheduleDTO::new)
//                .collect(Collectors.toList());
//    }

    public RouteDTO(Route route,List<BusScheduleDTO> schedules) {
        this.routeID = route.getRouteID();
        this.routeFrom = route.getRouteFrom();
        this.routeTo = route.getRouteTo();
        this.distance = route.getDistance();
        this.schedules = schedules;

    }
}
