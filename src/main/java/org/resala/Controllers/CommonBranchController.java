package org.resala.Controllers;

import org.springframework.http.ResponseEntity;

public interface CommonBranchController {
    ResponseEntity<Object> getAllByBranchId(int branchId);

    ResponseEntity<Object> getAllByMyBranchId();
}
