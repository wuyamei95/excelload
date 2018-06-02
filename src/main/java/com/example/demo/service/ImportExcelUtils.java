package com.example.demo.service;

import com.example.demo.pojo.bo.ExcelData;
import com.example.demo.pojo.bo.ExcelUtil;
import com.example.demo.pojo.bo.class_teacher;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtils {
    public List<class_teacher> importData(File file)
    {
        Workbook wb = null;
        List<class_teacher> class_teacherList = new ArrayList();
        try
        {
            if (ExcelUtil.isExcel2007(file.getPath())) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        for (int i = 0; i < sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            String class_name= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            Integer teacher_id = (int)row.getCell(1).getNumericCellValue();
            if (teacher_id==0 && class_name==null)
            {
                break;
            }
            class_teacher class_teacher1 =new class_teacher();
            class_teacher1.setClass_name(class_name);
            class_teacher1.setTeacher_id(teacher_id);
            class_teacherList.add(class_teacher1);
        }
        return class_teacherList;
    }

}

