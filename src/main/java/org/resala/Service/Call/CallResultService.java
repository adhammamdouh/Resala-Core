package org.resala.Service.Call;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Call.CallResult;
import org.resala.Repository.Call.CallResultRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CallResultService {
    @Autowired
    CallResultRepo callResultRepo;


    public CallResult getById(int id){
        Optional<CallResult> callResult= callResultRepo.getById(id);
        if(!callResult.isPresent())
            throw new MyEntityNotFoundException("call result "+ StaticNames.notFound);
        return callResult.get();
    }

    public CallResult getByName(String callResultName) {
        Optional<CallResult> callResult= callResultRepo.getByName(callResultName);
        if(!callResult.isPresent())
            throw new MyEntityNotFoundException("call result "+ StaticNames.notFound);
        return callResult.get();
    }
}
