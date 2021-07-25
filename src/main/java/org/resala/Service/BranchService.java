package org.resala.Service;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Repository.BranchRepo;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BranchService implements CommonCRUDService<BranchDTO>, CommonService<Branch> {
    @Autowired
    BranchRepo branchRepo;


    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Override
    public Branch getById(int id) {
        Optional<Branch> optionalBranch = branchRepo.findByIdAndOrganization_Id(id, TokenService.getOrganizationId());
        if (!optionalBranch.isPresent())
            throw new MyEntityNotFoundException("Branch " + StaticNames.notFound);
        return optionalBranch.get();
    }

    @Override
    public List<Branch> getAll() {
        int orgId = TokenService.getOrganizationId();
        return branchRepo.findAllByOrganization_Id(orgId);
    }

    public List<Branch> getBranchByIds(List<Integer> ids) {
        List<Branch> branches = branchRepo.findAllByIdInAndOrganization_Id(ids, TokenService.getOrganizationId());
        if (branches.size() != ids.size()) {
            ids.removeAll(branches.stream().map(Branch::getId).collect(toList()));
            throw new MyEntityNotFoundException("Branches with id's " + ids + " does not exist");
        }
        return branches;
    }


    @Override
    public ResponseEntity<Object> create(List<BranchDTO> dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> archive(BranchDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(BranchDTO newDto) {
        return null;
    }
}
