package org.resala.Models.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;*/
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "capital_id")
    @NotNull(message = "Please enter Capital")
    Capital capital;
    @Column(name = "additional_info")
    String additionalInfo;
    @Column(name = "apartment_number")
    @NotEmpty(message = "Please enter ApartmentNumber")
    String apartmentNumber;
    @Column(name = "building_number")
    @NotEmpty(message = "Please enter BuildingNumber")
    String buildingNumber;
    @Column(name = "street_name")
    @NotEmpty(message = "Please enter StreetName")
    String streetName;
    @Column(name = "region_name")
    @NotEmpty(message = "Please enter RegionName")
    String regionName;
}
