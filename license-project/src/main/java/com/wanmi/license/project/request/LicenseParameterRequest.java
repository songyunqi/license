package com.wanmi.license.project.request;

import com.foo.base.request.ARequest;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LicenseParameterRequest extends ARequest {

    private Long id;
    private Long projectId;
    private int consumerAmount;
    private String consumerType;
    private String holder;
    private String issuer;
    private String subject;
    private String notBefore;
    private String notAfter;
    private String info;
    private String extra;
}
