package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
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
import org.resala.StaticNames;
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



    public List<NetworkTypeAssignedToVolunteerProjection> getNetworkTypeAssignedToVolunteer(int eventId, int branchId){
        Branch branch=branchService.getById(branchId);
        Event event=eventService.getById(eventId);

        return networkAssignedToVolunteersRepo.getAllByEvent_IdAndBranch_Id(event.getId(),branch.getId(),NetworkTypeAssignedToVolunteerProjection.class);

    }

    public NetworkAssignedToVolunteers getByBranchAndEventAndVolunteer(int branchId,int eventId,int volunteerId){
        Optional<NetworkAssignedToVolunteers> optional =
                networkAssignedToVolunteersRepo.getByEvent_IdAndBranch_IdAndVolunteer_Id
                        (eventId,branchId,volunteerId);
        if(!optional.isPresent()){
            throw new MyEntityNotFoundException("network assigned to volunteer "+StaticNames.notFound);
        }
        return optional.get();
    }

    public ResponseEntity<Object> saveAndUpdate(List<NetworkAssignedToVolunteersDTO> dtos, int branchId){
        List<Pair<Integer,String>> failed= new ArrayList<>();
        for(int i=0;i< dtos.size();++i) {
            NetworkAssignedToVolunteersDTO dto=dtos.get(i);
            try {
                Event event = eventService.getById(dto.getEvent().getId());
                Branch branch = branchService.getById(branchId);

                if(callsService.countAllByEventAndBranch(event,branch)>0){
                    throw new MyEntityFoundBeforeException(StaticNames.callsHasBeenCreatedBefore);
                }

                NetworkType networkType = networkTypeService.getById(dto.getNetworkType().getId());
                Volunteer volunteer = volunteerService.getById(dto.getVolunteer().getId());


                NetworkAssignedToVolunteers networkAssignedToVolunteers;
                try {
                    networkAssignedToVolunteers = getByBranchAndEventAndVolunteer(branch.getId(), event.getId(), volunteer.getId());
                } catch (MyEntityNotFoundException ex) {
                    networkAssignedToVolunteers = new NetworkAssignedToVolunteers();
                }
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
