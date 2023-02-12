package com.aeropuerto.aeropuertoapi.modules.plane;

import com.aeropuerto.aeropuertoapi.modules.airline.Airline;
import com.aeropuerto.aeropuertoapi.modules.flight.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "planes")

public class Plane extends Airline{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Integer capacity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plane plane = (Plane) o;
        return id != null && Objects.equals(id, plane.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
