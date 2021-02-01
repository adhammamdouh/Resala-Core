package org.resala.Models.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;
    @ManyToOne(cascade = CascadeType.ALL)
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
