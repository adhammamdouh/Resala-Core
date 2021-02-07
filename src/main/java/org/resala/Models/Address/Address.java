package org.resala.Models.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

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
    Capital capital;
    @Column(name = "additional_info")
    String additionalInfo;
    @Column(name = "apartment_number")
    String apartmentNumber;
    @Column(name = "building_number")
    String buildingNumber;
    @Column(name = "street_name")
    String streetName;
    @Column(name = "region_name")
    String regionName;
}
