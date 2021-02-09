package org.resala.Service.Call;

import org.aspectj.weaver.ast.Call;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Call.CallType;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Repository.Call.CallTypeRepo;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.Call.CallTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CallTypeService {
    @Autowired
    CallTypeRepo callTypeRepo;



    public CallType getCallTypeByName(String networkName){
        Optional<CallType> callTypeOptional = callTypeRepo.findAllByName(networkName);
        if(!callTypeOptional.isPresent()){
            throw new MyEntityNotFoundException("network type "+ StaticNames.notFound);
        }
        return callTypeOptional.get();
    }

    public List<CallType> getCallTypeByNames(List<Integer> ids){
        List<CallType> callTypes = callTypeRepo.findAllById(ids);
        if (callTypes.size() != ids.size()) {
            ids.removeAll(callTypes.stream().map(CallType::getId).collect(toList()));
            throw new MyEntityNotFoundException("callTypes with id's " + ids + " does not exist");
        }
        return callTypes;

    }

}
