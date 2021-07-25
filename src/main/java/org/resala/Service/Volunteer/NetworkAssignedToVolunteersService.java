package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.NetworkAssignedToVolunteers;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Pair;
import org.resala.Projections.Calls.NetworkTypeAssignedToVolunteerProjection;
import org.resala.Repository.Volunteer.NetworkAssignedToVolunteersRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Call.CallTypeService;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Call.NetworkTypeService;
import org.resala.Service.CommonService;
import org.resala.Service.Event.EventService;
import org.resala.Service.TokenService;
import org.resala.StaticNames;
import org.resala.dto.Call.VolunteerToCallsDTO;
import org.resala.dto.Volunteer.NetworkAssignedToVolunteersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NetworkAssignedToVolunteersService implements CommonService<NetworkAssignedToVolunteers> {

    @Autowired
    NetworkAssignedToVolunteersRepo networkAssignedToVolunteersRepo;

    @Autowired
    NetworkTypeService networkTypeService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    EventService eventService;

    @Autowired
    CallTypeService callTypeService;

    @Autowired
    BranchService branchService;

    @Autowired
    CallsService callsService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public  <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> modelMapper().map(entity, outCLass))
                .collect(Collectors.toList());
    }




    @Override
    public NetworkAssignedToVolunteers getById(int id) {
        Optional<NetworkAssignedToVolunteers> volunteerAssignedCallsToEvent=
                networkAssignedToVolunteersRepo.getById(id);
        if(!volunteerAssignedCallsToEvent.isPresent())
            throw new MyEntityNotFoundException("assigned calls " + StaticNames.notFound);

        return volunteerAssignedCallsToEvent.get();
    }

    @Override
    public List<NetworkAssignedToVolunteers> getAll() {
        return null;
    }



    public List<NetworkTypeAssignedToVolunteerProjection> getNetworkTypeAssignedToVolunteer(int eventId){
        int branchId= TokenService.getBranchId();
        Event event=eventService.getById(eventId);

        if(!eventService.checkEventStatus(event))
            throw new ConstraintViolationException(StaticNames.eventIsNotActive);

        return networkAssignedToVolunteersRepo.getAllByEvent_IdAndBranch_Id(event.getId(),branchId,NetworkTypeAssignedToVolunteerProjection.class);

    }

    public Optional<NetworkAssignedToVolunteers> getByBranchAndEventAndNetwork(int branchId,int eventId,int networkId){
        Optional<NetworkAssignedToVolunteers> optional =
                networkAssignedToVolunteersRepo.getByEvent_IdAndBranch_IdAndNetworkType_Id
                        (eventId,branchId,networkId);
        return optional;
    }

    public ResponseEntity<Object> saveAndUpdate(VolunteerToCallsDTO volunteerToCallsDTO, int branchId){
        List<Pair<Integer,String>> failed= new ArrayList<>();

        Event event = eventService.getById(volunteerToCallsDTO.getEvent().getId());
        Branch branch = branchService.getById(branchId);

        if(!event.getBranches().contains(branch)){
            throw new ConstraintViolationException(StaticNames.eventShouldContainsTheBranch);
        }

        if(!eventService.checkEventStatus(event))
            throw new ConstraintViolationException(StaticNames.eventIsNotActive);

        List<NetworkAssignedToVolunteersDTO> dtos=volunteerToCallsDTO.getNetworkAssignedToVolunteers();

        for(int i=0;i< dtos.size();++i) {
            NetworkAssignedToVolunteersDTO dto=dtos.get(i);
            try {

                if(callsService.countAllByEventAndBranch(event,branch)>0){
                    throw new MyEntityFoundBeforeException(StaticNames.callsHasBeenCreatedBefore);
                }

                NetworkType networkType = networkTypeService.getById(dto.getNetworkType().getId());
                Volunteer volunteer = volunteerService.getById(dto.getVolunteer().getId());


                Optional<NetworkAssignedToVolunteers> optionalNetworkAssignedToVolunteers;

                optionalNetworkAssignedToVolunteers = getByBranchAndEventAndNetwork(branch.getId(), event.getId(), networkType.getId());

                NetworkAssignedToVolunteers networkAssignedToVolunteers = new NetworkAssignedToVolunteers();

                if(optionalNetworkAssignedToVolunteers.isPresent())
                    networkAssignedToVolunteers=optionalNetworkAssignedToVolunteers.get();

                networkAssignedToVolunteers.setBranch(branch);
                networkAssignedToVolunteers.setEvent(event);
                networkAssignedToVolunteers.setVolunteer(volunteer);
                networkAssignedToVolunteers.setNetworkType(networkType);

                networkAssignedToVolunteersRepo.save(networkAssignedToVolunteers);
            }
            catch(Exception e){
                failed.add(new Pair<>(i,e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    public List<NetworkAssignedToVolunteers> getByEventIdAndBranchId(int eventId, int branchId){

        List<NetworkAssignedToVolunteers> networkAssignedToVolunteers =
                networkAssignedToVolunteersRepo.getAllByEvent_IdAndBranch_id(eventId,branchId);

        if(networkAssignedToVolunteers ==null || networkAssignedToVolunteers.isEmpty())
            throw new MyEntityNotFoundException("Assigned Calls " +StaticNames.notFound);

        return networkAssignedToVolunteers;
    }


}
