package org.resala.Controllers;

import org.springframework.http.ResponseEntity;

public interface CommonActiveBranchStateController {
    ResponseEntity<Object> getAllActiveByBranchId(int branchId);

    ResponseEntity<Object> getAllActiveByMyBranchId();

    ResponseEntity<Object> getAllArchivedByBranchId(int branchId);

    ResponseEntity<Object> getAllArchivedByMyBranchId();
}
