package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.Shirt;
import org.resala.Repository.Volunteer.ShirtRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShirtService {
    @Autowired
    ShirtRepo shirtRepo;
    public Shirt getById(int id) {
        Optional<Shirt> optionalTShirt = shirtRepo.findById(id);
        if (!optionalTShirt.isPresent())
            throw new MyEntityNotFoundException("T-Shirt "+ StaticNames.notFound);
        return optionalTShirt.get();
    }
}
