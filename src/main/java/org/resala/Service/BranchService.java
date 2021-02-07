package org.resala.Service;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Repository.BranchRepo;
import org.resala.dto.BranchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BranchService implements CommonService<BranchDTO> {
    @Autowired
    BranchRepo branchRepo;

    @Override
    public ResponseEntity<Object> create(BranchDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(BranchDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(BranchDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }

    public Branch getBranchById(int id) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        if (!optionalBranch.isPresent())
            throw new MyEntityNotFoundException("Branch Not Found");
        return optionalBranch.get();
    }

    public List<Branch> getBranchByIds(List<Integer> ids) {
        List<Branch> branches = branchRepo.findAllById(ids);
        if (branches.size() != ids.size()) {
            ids.removeAll(branches.stream().map(Branch::getId).collect(toList()));
            throw new MyEntityNotFoundException("Branches with id's " + ids + " does not exist");
        }
        return branches;
    }

}
