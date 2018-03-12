package com.wanmi.license.project.service;

import com.foo.base.service.CService;
import com.wanmi.license.project.domain.LicenseParameter;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.mapper.ProjectMapper;
import com.wanmi.license.project.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ProjectService extends CService<Project, Long, ProjectRequest> {

    @Autowired
    ProjectMapper projectMapper;

    public List<Project> list(ProjectRequest request) {
        List<Project> list = projectMapper.list(request);
        return list;
    }

    public Project getOne(Long aLong) {
        return projectMapper.getOne(aLong);
    }

    public int save(Project project) {
        return 0;
    }

    public int delete(List<Long> longs) {
        return 0;
    }

    public int update(List<Project> list) {
        return 0;
    }

    public int batchImport(List<Project> list) {
        return 0;
    }

    public int initProject(Project project) throws IOException {
        String companyName = project.getCompanyName();
        String defaultPassword = project.getDefaultPassword();
        String licensingSubject = project.getLicensingSubject();

        String groupId = project.getGroupId();
        String artifactId = project.getArtifactId();

        StringBuilder cmd = new StringBuilder();
        cmd.append(" mvn archetype:generate -B ");
        cmd.append(" -DarchetypeArtifactId=truelicense-maven-archetype");
        cmd.append(" -DarchetypeGroupId=net.java.truelicense");
        cmd.append(" -DarchetypeVersion=2.4.1");
        cmd.append(" -DartifactId=").append(artifactId);
        cmd.append(" -DcompanyName=").append("\"").append(companyName).append("\"");
        cmd.append(" -DdefaultPassword=").append(defaultPassword);
        cmd.append(" -DgroupId=").append(groupId);
        cmd.append(" -DlicensingSubject=").append("\"").append(licensingSubject).append("\"");
        cmd.append(" -Dversion=1.0-SNAPSHOT");

        ProcessBuilder builder = new ProcessBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd.toString());
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(" init done=====================");

        return 0;
    }

    public void genLicense(Project project, LicenseParameter parameters) throws IOException {
        //java -jar wm_license-keygen/target/wm_license-keygen-1.0-SNAPSHOT-standalone.jar create wm.license -verbose true -input e:\\truelicense\\lic.txt

        String groupId = project.getGroupId();
        String artifactId = project.getArtifactId();
        String companyName = project.getCompanyName();

        String filePath = genLicenseInitFile(parameters);

        StringBuilder cmd = new StringBuilder();
        cmd.append(" java -jar ");
        cmd.append(artifactId).append("-keygen/target/").append(artifactId).append("-keygen-1.0-SNAPSHOT-standalone.jar");
        cmd.append(" create").append(companyName).append(".license");
        cmd.append(" -verbose true");
        cmd.append(" -input ").append(filePath);

        ProcessBuilder builder = new ProcessBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd.toString());
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(" init done=====================");
    }

    public String genLicenseInitFile(LicenseParameter parameters) {

        return null;
    }

    public Long selectProjectCount(ProjectRequest request) {
        Long count = projectMapper.selectProjectCount(request);
        return count;
    }
}
