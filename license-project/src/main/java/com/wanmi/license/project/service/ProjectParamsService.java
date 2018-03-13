package com.wanmi.license.project.service;

import com.foo.base.service.CService;
import com.wanmi.license.project.domain.LicenseParameter;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.mapper.LicenseParameterMapper;
import com.wanmi.license.project.mapper.ProjectMapper;
import com.wanmi.license.project.request.LicenseParameterRequest;
import com.wanmi.license.project.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ProjectParamsService extends CService<LicenseParameter, Long, LicenseParameterRequest> {

    @Autowired
    LicenseParameterMapper licenseParameterMapper;

    public LicenseParameter getOne(Long aLong) {
        return licenseParameterMapper.getOne(aLong);
    }

    public LicenseParameter getOneByProjectId(Long aLong) {
        return licenseParameterMapper.selectByProjectId(aLong);
    }

    public int save(LicenseParameter licenseParameter) {

        if(licenseParameter.getId()!=null){
            licenseParameterMapper.updateById(licenseParameter);
        }else{
            licenseParameterMapper.save(licenseParameter);
        }

        return 1;
    }

    public int delete(List<Long> longs) {
        return 0;
    }

    public int update(List<LicenseParameter> list) {
        return 0;
    }

    public int batchImport(List<LicenseParameter> list) {
        return 0;
    }

    @Override
    public List<LicenseParameter> list(LicenseParameterRequest licenseParameterRequest) {
        return null;
    }


}
