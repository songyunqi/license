package com.wanmi.license.project.service;

import com.foo.base.service.CService;
import com.wanmi.license.project.domain.LicenseParameter;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.mapper.LicenseParameterMapper;
import com.wanmi.license.project.request.LicenseParameterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectParamsService extends CService<LicenseParameter, Long, LicenseParameterRequest> {

    @Autowired
    LicenseParameterMapper licenseParameterMapper;

    @Autowired
    private ProjectService projectService;

    public LicenseParameter getOne(Long aLong) {
        return licenseParameterMapper.getOne(aLong);
    }

    public LicenseParameter getOneByProjectId(Long aLong) {
        return licenseParameterMapper.selectByProjectId(aLong);
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(LicenseParameter licenseParameter) {
        Project project = projectService.getOne(licenseParameter.getProjectId());

        LicenseParameter oldParameter = this.getOneByProjectId(licenseParameter.getProjectId());

        licenseParameter.setSubject(project.getLicensingSubject());

        if(Objects.nonNull(oldParameter)){
            licenseParameter.setId(oldParameter.getId());
            licenseParameterMapper.updateById(licenseParameter);
        }else{
            licenseParameterMapper.save(licenseParameter);
        }

        try {
            projectService.genLicense(project, licenseParameter);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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
