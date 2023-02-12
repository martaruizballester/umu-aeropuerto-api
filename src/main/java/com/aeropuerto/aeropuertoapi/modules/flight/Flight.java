package com.aeropuerto.aeropuertoapi.modules.flight;

import com.aeropuerto.aeropuertoapi.modules.airline.Airline;
import com.aeropuerto.aeropuertoapi.modules.plane.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "flights")

public class Flight extends Plane{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate flightDate;
    private LocalTime departureTime;

    private LocalTime takeoffTime;
    private String origin;
    private String destination;
    private Integer status;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plane_id")
    private Plane plane;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Flight flight = (Flight) o;
        return id != null && Objects.equals(id, flight.id);
    }



    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
