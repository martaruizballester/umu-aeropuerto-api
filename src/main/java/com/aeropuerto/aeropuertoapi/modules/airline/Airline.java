package com.aeropuerto.aeropuertoapi.modules.airline;

import com.aeropuerto.aeropuertoapi.modules.flight.Flight;
import com.aeropuerto.aeropuertoapi.modules.plane.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "airlines")
@Inheritance(strategy = InheritanceType.JOINED)
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numPlanes;
    @JsonIgnore
    @OneToMany(mappedBy = "airline")
    private List<Plane> planes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Airline airline = (Airline) o;
        return id != null && Objects.equals(id, airline.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}



