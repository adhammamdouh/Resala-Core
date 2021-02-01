package org.resala.Models.Address;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "capital")
public class Capital {
    @Column(name = "capital_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name")
    String name;
    @OneToMany(mappedBy = "capital", cascade = CascadeType.ALL)
    private List<Address> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
