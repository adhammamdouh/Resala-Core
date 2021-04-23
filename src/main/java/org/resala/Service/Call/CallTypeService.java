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
            throw new MyEntityNotFoundException("call type "+ StaticNames.notFound);
        }
        return callTypeOptional.get();
    }
    public CallType getCallTypeById(int id){
        Optional<CallType> callTypeOptional = callTypeRepo.findById(id);
        if(!callTypeOptional.isPresent()){
            throw new MyEntityNotFoundException("call type "+ StaticNames.notFound);
        }
        return callTypeOptional.get();
    }




}
