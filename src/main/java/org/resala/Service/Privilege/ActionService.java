package org.resala.Service.Privilege;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;
import org.resala.Repository.Privilege.ActionRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.dto.Privilege.ActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService implements CommonService<Action> {
    @Autowired
    ActionRepo actionRepo;

    @Override
    public Action getById(int id) {
        Optional<Action> optional = actionRepo.findById(id);
        if (!optional.isPresent())
            throw new MyEntityNotFoundException(id + " Action Not Found");
        return optional.get();
    }

    @Override
    public List<Action> getAll() {
        return null;
    }
}
