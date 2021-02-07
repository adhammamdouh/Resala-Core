package org.resala.Repository.Address;

import org.resala.Models.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
