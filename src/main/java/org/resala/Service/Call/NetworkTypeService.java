package org.resala.Service.Call;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Call.NetworkType;
import org.resala.Repository.Call.NetworkTypeRepo;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class NetworkTypeService {
    @Autowired
    NetworkTypeRepo networkTypeRepo;

    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public NetworkType getNetworkTypeByName(String networkName){
        Optional<NetworkType> NetworkTypeOptional = networkTypeRepo.findAllByName(networkName);
        if(!NetworkTypeOptional.isPresent()){
            throw new MyEntityNotFoundException("network type "+ StaticNames.notFound);
        }
        return NetworkTypeOptional.get();
    }

    public NetworkType getById(int id){
        Optional<NetworkType> NetworkTypeOptional = networkTypeRepo.findAllById(id);
        if(!NetworkTypeOptional.isPresent()){
            throw new MyEntityNotFoundException("network type "+ StaticNames.notFound);
        }
        return NetworkTypeOptional.get();
    }

    public List<NetworkType> getNetworksTypeById(List<Integer> ids){
        List<NetworkType> NetworkTypes = networkTypeRepo.findAllById(ids);
        if (NetworkTypes.size() != ids.size()) {
            ids.removeAll(NetworkTypes.stream().map(NetworkType::getId).collect(toList()));
            throw new MyEntityNotFoundException("NetworkTypes with id's " + ids + " does not exist");
        }
        return NetworkTypes;

    }

    public NetworkType getNetworkTypeBasedOnVolunteerNumber(String number){
        number=number.substring(0,3);
        switch (number){
            case "010":
                return getNetworkTypeByName(StaticNames.vodafone);
            case "011":
                return getNetworkTypeByName(StaticNames.etisalat);
            case "012":
                return getNetworkTypeByName(StaticNames.orange);
            case "015":
                return getNetworkTypeByName(StaticNames.we);
            default:
                throw new MyEntityNotFoundException("Invalid phone number");
        }

    }

}
