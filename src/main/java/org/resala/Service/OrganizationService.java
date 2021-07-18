package org.resala.Service;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Models.Organization;
import org.resala.Repository.OrganizationRepo;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.OrganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService implements CommonCRUDService<OrganizationDTO>, CommonService<Organization>{
    @Autowired
    private OrganizationRepo organizationRepo;
    @Override
    public ResponseEntity<Object> create(List<OrganizationDTO> dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> archive(OrganizationDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(OrganizationDTO newDto) {
        return null;
    }

    @Override
    public Organization getById(int id) {
        Optional<Organization> optionalOrganization = organizationRepo.findById(id);
        if (!optionalOrganization.isPresent())
            throw new MyEntityNotFoundException("Organization "+ StaticNames.notFound);
        return optionalOrganization.get();
    }

    public Organization getByDomainName(String domainName) {
        Optional<Organization> optionalOrganization = organizationRepo.findByDomainNameEndingWith(domainName);
        if (!optionalOrganization.isPresent())
            throw new MyEntityNotFoundException("Organization Domain "+ StaticNames.notFound);
        return optionalOrganization.get();
    }

    @Override
    public List<Organization> getAll() {
        return null;
    }
}
