package com.wanmi.license.project.service;

import com.alibaba.fastjson.JSON;
import com.foo.base.service.CService;
import com.wanmi.license.project.domain.LicenseParameter;
import com.wanmi.license.project.domain.Project;
import com.wanmi.license.project.mapper.ProjectMapper;
import com.wanmi.license.project.request.ProjectRequest;
import com.wanmi.license.project.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        cmd.append(" -DgroupId=").append(groupId);
        cmd.append(" -DartifactId=").append(artifactId);
        cmd.append(" -DcompanyName=").append("\"").append(companyName).append("\"");
        cmd.append(" -DdefaultPassword=").append(defaultPassword);
        cmd.append(" -DlicensingSubject=").append("\"").append(licensingSubject).append("\"");
        cmd.append(" -Dversion=1.0-SNAPSHOT");

        String fileDir = dir + File.separator + fileName;
        file = new File(fileDir);
        writerShell(cmd, file);
        System.out.println("cmd--->" + cmd);

//        exec(fileDir);

        //打jar包
        cmd.delete(0, cmd.length());
        cmd.append(" cd " + dir + File.separator + artifactId);
        cmd.append("\n");
        cmd.append(" mvn clean install -Pintegration-test ");

        writerShell(cmd, file);
//        exec(fileDir);

        //E:\license\license1\license1-keymgr\target  license1-keymgr-1.0-SNAPSHOT-standalone

        System.out.println("请到 " + newPoj + " 目录下拷贝对应的jar包");
        file.delete();
        return 1;
    }


    private void writerShell(StringBuilder cmd, File file) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(cmd.toString());
        out.close();

        exec(file.getCanonicalPath());
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

    /**
     * 产生license 文件
     *
     * @param project
     * @param parameters
     * @throws IOException
     */
    public void genLicense(Project project, LicenseParameter parameters) throws IOException {
        //java -jar wm_license-keygen/target/wm_license-keygen-1.0-SNAPSHOT-standalone.jar create wm.license -verbose true -input e:\\truelicense\\lic.txt

//        String groupId = project.getGroupId();
        String artifactId = project.getArtifactId();
        String companyName = project.getCompanyName();

        String filePath = genLicenseInitFile(parameters);
        StringBuilder cmd = new StringBuilder();
        String osName = System.getProperty("os.name");
        String dir;
        String fileName = "";
        if (osName.indexOf("Windows") > -1) {
            dir = windowDir;
            fileName = "license_params.bat";
        } else {
            dir = LiunxDir;
            fileName = "license_params.sh";
        }

        //license文件生成在 dir 目录下
        cmd.append("cd " + dir);
        cmd.append(" \n");
        cmd.append(" java -jar ");
        cmd.append(artifactId + "/").append(artifactId).append("-keygen/target/").append(artifactId).append("-keygen-1.0-SNAPSHOT-standalone.jar");
        cmd.append(" create ").append(companyName).append(".license");
        cmd.append(" -verbose true");
        cmd.append(" -input ").append(filePath);

        File shellFile = new File(dir + fileName);
        writerShell(cmd, shellFile);


        //license 文件生成后再生成jar包

        //创建jar打包文件夹
        String jarDir = dir + File.separator + "jar";
        File directory = new File(jarDir);

        //删除jar 文件
        FileUtils.deleteQuietly(directory);

        if (!directory.exists()) {
            directory.mkdir();
        } else {
            File[] listFile = directory.listFiles();
            Optional<File> optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf("jar") > 0).findFirst();
            //删除之前copy的jar
            if (optFile.isPresent()) {
                optFile.get().delete();
            }
        }

        //copy jar 到解压目录
        FileUtil.BatCopyFileFromJar(File.separator + "*.jar", jarDir);

        //解压kstore_license-0.0.1-SNAPSHOT.jar jar包
        cmd.delete(0, cmd.length());
        cmd.append(" cd " + jarDir);
        cmd.append("\n");
        cmd.append(" jar xvf *.jar ");
        writerShell(cmd, shellFile);
        //删除 store_license-0.0.1-SNAPSHOT.jar
        File[] listFile = directory.listFiles();
        Optional<File> optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf("jar") > 0).findFirst();
        if (optFile.isPresent()) {
            optFile.get().delete();
        }

        //删除license文件
        optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf(".license") > 0).findFirst();
        if (optFile.isPresent()) {
            optFile.get().delete();
        }

        //到lib下删除之前的jar包
        directory = new File(jarDir + File.separator + "lib");
        listFile = directory.listFiles();
        optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf("jar") > 0).findFirst();
        if (optFile.isPresent()) {
            optFile.get().delete();
        }

        //copy jar到lib文件夹
        String fileJar = dir + File.separator + artifactId + File.separator + artifactId + "-keymgr" + File.separator + "target" + File.separator + artifactId + "-keymgr-1.0-SNAPSHOT-standalone.jar";
        String newFileJar = jarDir + File.separator + "lib" + File.separator + artifactId + "-keymgr-1.0-SNAPSHOT-standalone.jar";
        InputStream inputStream = new FileInputStream(fileJar);
        FileUtil.write2File(inputStream, newFileJar);

        String licFile = dir + File.separator + companyName + ".license";
        String newLicFile = jarDir + File.separator + companyName + ".license";
        inputStream = new FileInputStream(licFile);
        FileUtil.write2File(inputStream, newLicFile);

        //打包成最后的jar包
        cmd.delete(0, cmd.length());
        cmd.append(" cd " + jarDir);
        cmd.append("\n");
        cmd.append(" jar cvf ").append(artifactId).append("_license-0.0.1-SNAPSHOT.jar ").append(" ./ ");
        writerShell(cmd, shellFile);

        System.out.println("最终jar 生成 "+jarDir+"/"+artifactId+"_license-0.0.1-SNAPSHOT.jar ");
    }


    public String genLicenseInitFile(LicenseParameter parameters) throws IOException {

        String osName = System.getProperty("os.name");
        String dir;
        if (osName.indexOf("Windows") > -1) {
            System.out.println(windowDir);
            dir = windowDir;
        } else {
            dir = LiunxDir;
        }

        parameters.setIssuer("CN="+parameters.getIssuer());

        String fileDir =  dir + "/lic.txt";
        fileDir = fileDir.replace("\\", "/");

        File file = new File(fileDir);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        String json = JSON.toJSONString(parameters);
        out.write(json);
        out.close();

        return fileDir;
    }

    public Long selectProjectCount(ProjectRequest request) {
        Long count = projectMapper.selectProjectCount(request);
        return count;
    }

    public static void main(String[] args) throws IOException {
        String jarDir = "E:\\license\\jar\\test.txt";

        File directory = new File(jarDir);
        directory.mkdir();

//        FileUtils.deleteQuietly(directory);


//        directory.mkdir();



//         directory = new File(jarDir);
//        File[] listFile = directory.listFiles();
//        Optional<File> optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf("jar") > 0).findFirst();
//        optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf(".license") > 0).findFirst();
//        System.out.println(optFile.isPresent());
//        System.out.println(optFile.get().getName());
//        optFile.get().delete();

        LicenseParameter parameter = new LicenseParameter();
        parameter.setId(1L);

        System.out.println(JSON.toJSONString(parameter));
//
//        String dir = "E:\\license";
//        File directory = new File(dir);
//        File[] listFile = directory.listFiles();
//
//        Arrays.stream(listFile).forEach(System.out::println);
//
//        Optional<File> optFile = Arrays.stream(listFile).filter(file1 -> file1.getName().indexOf("jar") > 0).findFirst();
//
//        System.out.println(optFile.get().getCanonicalPath());

//
//        StringBuilder cmd = new StringBuilder();
//        String osName = System.getProperty("os.name");
//        String dir;
//        String fileName = "";
//        if (osName.indexOf("Windows") >= 0) {
//            dir = "E:\\";
//            fileName = "license.bat";
//        } else {
//            dir = "/data/";
//            fileName = "license.sh";
//        }
//
//        File file = new File(dir);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//
//
//        cmd.append("cd " + dir);
//        cmd.append("\n");
//        cmd.append(" ping wwww.baidu.com");
//        String fileDir = dir + File.separator + fileName;
//        file = new File(fileDir);
//
//        BufferedWriter out = new BufferedWriter(new FileWriter(fileDir));
//        out.write(cmd.toString());
//        out.close();
//
////        StringBuilder cmd = new StringBuilder();
////        cmd.append("cd E:/");
////
////        String[] cmds = new String[3];
////        cmds[0] = "cmd.exe" ;
////        cmds[1] = "/C" ;
////        cmds[2] = "cd E:/ ls";
//
//        ProcessBuilder builder = new ProcessBuilder();
//        Runtime runtime = Runtime.getRuntime();
//        Process process = runtime.exec(fileDir);
//        InputStream inputStream = process.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        System.out.println(" init done=====================");
//        file.delete();
    }
}
