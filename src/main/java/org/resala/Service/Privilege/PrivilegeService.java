package org.resala.Service.Privilege;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;
import org.resala.Pair;
import org.resala.Repository.Privilege.PrivilegeRepo;
import org.resala.Service.*;
import org.resala.StaticNames;
import org.resala.dto.Privilege.ActionDTO;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PrivilegeService implements CommonCRUDService<PrivilegeDTO>, CommonService<Privilege> {
    @Autowired
    PrivilegeRepo privilegeRepo;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    private ActionService actionService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public ResponseEntity<Object> create(List<PrivilegeDTO> dtos) {
        ArrayList<Pair<String, String>> failed = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {

            Privilege privilege;
            try {
                PrivilegeDTO dto = dtos.get(i);
                try {
                    getPrivilegeByName(dto.getName());//make that to catch MyEntityNotFoundException to create new priv
                    throw new MyEntityFoundBeforeException("Privilege Name is already exist");
                } catch (MyEntityNotFoundException ex) {
                    privilege = modelMapper().map(dto, Privilege.class);
                }
                privilege.setId(0);
                checkConstraintViolations(privilege);
                privilege.setOrganization(organizationService.getById(TokenService.getOrganizationId()));

                privilege.setActions(new ArrayList<>());
                setActionsAndSave(failed, i, privilege, dto);
            } catch (Exception e) {
                failed.add(new Pair<>(String.valueOf(i), e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    private void setActionsAndSave(ArrayList<Pair<String, String>> failed, int i, Privilege privilege, PrivilegeDTO dto) {
        if (dto.getActions() != null) {
            for (int j = 0; j < dto.getActions().size(); j++) {
                try {
                    ActionDTO actionDTO = dto.getActions().get(j);
                    Action action = actionService.getById(actionDTO.getId());
                    if(!privilegeRepo.findAllByIdAndActions_id(privilege.getId(),action.getId()).isPresent())
                        privilege.getActions().add(action);
                } catch (Exception e) {
                    failed.add(new Pair<>(i + "." + j, e.getMessage()));
                }
            }
        }
        privilegeRepo.save(privilege);
    }

    @Override
    public ResponseEntity<Object> archive(PrivilegeDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(PrivilegeDTO newDto) {
        return null;
    }

    @Override
    public Privilege getById(int id) {
        Optional<Privilege> optional = privilegeRepo.findByIdAndOrganization_Id(id, TokenService.getOrganizationId());
        if (!optional.isPresent())
            throw new MyEntityNotFoundException(id + " Privilege Not Found");
        return optional.get();
    }

    @Override
    public List<Privilege> getAll() {
        return null;
    }

    public Privilege getPrivilegeByName(String name) {
        Optional<Privilege> optionalPrivilege = privilegeRepo.findByNameAndOrganization_Id(name, TokenService.getOrganizationId());
        if (!optionalPrivilege.isPresent())
            throw new MyEntityNotFoundException(name + " Privilege Not Found");
        return optionalPrivilege.get();
    }

    public void checkConstraintViolations(Privilege privilege) {
        CheckConstraintService.checkConstraintViolations(privilege, Privilege.class);
    }

    public ResponseEntity<Object> addPrivilegesActions(List<PrivilegeDTO> dtos) {
        ArrayList<Pair<String, String>> failed = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            try {
                PrivilegeDTO dto = dtos.get(i);
                Privilege privilege = getById(dto.getId());
                setActionsAndSave(failed, i, privilege, dto);
            } catch (Exception e) {
                failed.add(new Pair<>(String.valueOf(i), e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    public List<Privilege> findByIds(List<Integer> ids) {
        List<Privilege> privileges = privilegeRepo.findAllByIdInAndOrganization_Id(ids, TokenService.getOrganizationId());
        if (privileges.size() != ids.size()) {
            ids.removeAll(privileges.stream().map(Privilege::getId).collect(toList()));
            throw new MyEntityNotFoundException("Privilege with id's " + ids + " does not exist");
        }
        return privileges;
    }
}
