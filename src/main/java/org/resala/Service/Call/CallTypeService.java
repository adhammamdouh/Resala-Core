package org.resala.Service.Call;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Call.CallType;
import org.resala.Repository.Call.CallTypeRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CallTypeService {
    @Autowired
    CallTypeRepo callTypeRepo;


    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public CallType getCallTypeByName(String networkName){
        Optional<CallType> callTypeOptional = callTypeRepo.findByName(networkName);
        if(!callTypeOptional.isPresent()){
            throw new MyEntityNotFoundException("network type "+ StaticNames.notFound);
        }
        return callTypeOptional.get();
    }
    public CallType getCallTypeById(int id){
        Optional<CallType> callTypeOptional = callTypeRepo.findById(id);
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

    public CallType getCallTypeBasedOnVolunteerNumber(String number){
        number=number.substring(0,3);
        switch (number){
            case "010":
                return getCallTypeByName(StaticNames.vodafone);
            case "011":
                return getCallTypeByName(StaticNames.etisalat);
            case "012":
                return getCallTypeByName(StaticNames.orange);
            case "015":
                return getCallTypeByName(StaticNames.we);
            default:
                throw new MyEntityNotFoundException("wrong phone number");
        }

    }

}
