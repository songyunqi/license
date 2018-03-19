package com.wanmi.license.project.service;

import com.foo.base.service.CService;
import com.wanmi.license.project.domain.LicenseParameter;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.mapper.ProjectMapper;
import com.wanmi.license.project.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ProjectService extends CService<Project, Long, ProjectRequest> {

    private String LiunxDir = "/data/license";

    private String windowDir = "E:\\license";

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

        if (project.getId() != null) {
            projectMapper.updateById(project);
        } else {
            projectMapper.save(project);
        }

        int res = 0;
        try {
            res = initProject(project);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return res;
    }

    @Override
    public int delete(List<Long> longs) {
        return 0;
    }

    @Override
    public int update(List<Project> list) {
        return 0;
    }

    @Override
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
        String osName = System.getProperty("os.name");
        String dir;
        String fileName = "";
        if (osName.indexOf("Windows") > -1) {
            System.out.println(windowDir);
            dir = windowDir;
            fileName = "license.bat";
        } else {
            dir = LiunxDir;
            fileName = "license.sh";
        }

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }

        String newPoj = dir + File.separator + artifactId;
        File newPojFile = new File(newPoj);
        //项目名已经存在
        if (newPojFile.exists()) {
            return 3;
        }

        //生成pom文件
        cmd.append(" cd " + dir);
        cmd.append("\n");
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

        String fileDir = dir + File.separator + fileName;
        file = new File(fileDir);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(cmd.toString());
        out.close();

        exec(fileDir);

        //打jar包
        cmd.delete(0, cmd.length());
        cmd.append(" cd " + dir + File.separator + artifactId);
        cmd.append("\n");
        cmd.append(" mvn clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true");

        out = new BufferedWriter(new FileWriter(file));
        out.write(cmd.toString());
        out.close();

        exec(fileDir);

        file.delete();

        System.out.println("请到 "+newPoj+" 目录下拷贝对应的jar包");
        return 1;
    }


    private void exec(String fileDir) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(fileDir);
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
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

    public static void main(String[] args) throws IOException {
        StringBuilder cmd = new StringBuilder();
        String osName = System.getProperty("os.name");
        String dir;
        String fileName = "";
        if (osName.indexOf("Windows") >= 0) {
            dir = "E:\\";
            fileName = "license.bat";
        } else {
            dir = "/data/";
            fileName = "license.sh";
        }

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }


        cmd.append("cd " + dir);
        cmd.append("\n");
        cmd.append(" ping wwww.baidu.com");
        String fileDir = dir + File.separator + fileName;
        file = new File(fileDir);

        BufferedWriter out = new BufferedWriter(new FileWriter(fileDir));
        out.write(cmd.toString());
        out.close();

//        StringBuilder cmd = new StringBuilder();
//        cmd.append("cd E:/");
//
//        String[] cmds = new String[3];
//        cmds[0] = "cmd.exe" ;
//        cmds[1] = "/C" ;
//        cmds[2] = "cd E:/ ls";

        ProcessBuilder builder = new ProcessBuilder();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(fileDir);
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(" init done=====================");
        file.delete();
    }
}
