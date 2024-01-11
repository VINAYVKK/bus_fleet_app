package com.apsrtc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer routeID;

    @NotNull(message = "Start point cannot be null !")
	@NotBlank(message = "Start point cannot be blank !")
	@NotEmpty(message = "Start point cannot be empty !")
    private String routeFrom;

	@NotNull(message = "Destination point cannot be null !")
	@NotBlank(message = "Destination point cannot be blank !")
	@NotEmpty(message = "Destination point cannot be empty !")
    private String routeTo;
    private Integer distance;
    private List<BusSchedule> schedules = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    public List<BusSchedule> getSchedules() {
        return this.schedules;
    }


    public Route(String routeFrom, String routeTo, Integer distance) {
        this.routeFrom = routeFrom;
        this.routeTo = routeTo;
        this.distance = distance;
    }
}
