package org.resala.resala.Controllers;

import org.springframework.http.ResponseEntity;

public interface CommonBranchController {
    ResponseEntity<Object> getByBranchId(int branchId);

    ResponseEntity<Object> getByMyBranchId();
}
