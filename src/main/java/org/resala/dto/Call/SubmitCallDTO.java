package org.resala.dto.Call;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Call.CallResult;

@Setter
@Getter
public class SubmitCallDTO {
    int callId;
    CallTypeDTO callTypeDTO;
    String comment;
    CallResult callResult;
}
