package com.wanmi.license.web;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("district")
public class DistrictController {

    @Autowired
    private JdbcTemplate jdbc;

    @RequestMapping("compare")
    @ResponseBody
    public String compare() throws IOException {

        List<String> diffList = new ArrayList<>();

        List<String> dbList = loadDb();

        List<String> excelList = loadExcel("D:\\行政区划清单-V3.0 9.03.xlsx");

        int dbSize = 0;
        int excelSize = 0;

        if (CollectionUtils.isNotEmpty(dbList)) {
            dbSize = dbList.size();
        }
        if (CollectionUtils.isNotEmpty(excelList)) {
            excelSize = excelList.size();
        }

        for (int i = 0; i < excelSize; i++) {
            boolean found = false;
            String excelName = excelList.get(i);
            for (int j = 0; j < dbSize; j++) {
                String dbName = dbList.get(j);
                if (StringUtils.endsWith(excelName, dbName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                diffList.add(excelList.get(i));
            }
        }

        return String.join(",", diffList);
    }

    /**
     * 读取来自excel中的区域信息
     *
     * @return
     */
    private List<String> loadExcel(String path) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(path));
        List<String> result = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        Row row;
        Cell cell;
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);
            cell = row.getCell(3);
            String name1 = cell.getStringCellValue();
            cell = row.getCell(2);
            String name2 = cell.getStringCellValue();
            //北京市  北京市 过滤掉
            if (StringUtils.endsWith(name1, name2)) {
                continue;
            }
            result.add(name2 + "-" + name1);
        }
        return result;
    }


    private List<String> loadDb() {
        String sql = " select concat(b.city_name,\"-\", a.district_name) as district_name \n" +
                "from np_sys_district as a \n" +
                "left join np_sys_city b on a.city_id = b.city_id\n" +
                "where a.del_flag = '0'; ";

        List<Map<String, Object>> dbList = jdbc.queryForList(sql);
        List<String> result = new ArrayList<>();
        int size = 0;
        if (CollectionUtils.isNotEmpty(dbList)) {
            size = dbList.size();
        }
        Map<String, Object> tempMap;
        for (int i = 0; i < size; i++) {
            tempMap = dbList.get(i);
            if (tempMap.get("district_name") != null) {
                result.add(tempMap.get("district_name").toString());
            }
        }
        return result;
    }
}
