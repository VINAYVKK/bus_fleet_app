package com.apsrtc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusSchedule implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scheduleId;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private Route route;

//    @JsonBackReference
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="district_of_state")
//    public Statemaster getStatemaster() {
//        return this.statemaster;
//    }
    public Route getRoute(){
        return this.route;
    }

//    @JsonBackReference
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="district_of_state")
//    public Statemaster getStatemaster() {
//        return this.statemaster;
//    }

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;
}
