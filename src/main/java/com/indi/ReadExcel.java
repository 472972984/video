package com.indi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ChenHQ
 * @title: ReadExcel
 * @projectName video
 * @description: 读取Excel中单元格内容
 * @date 2019/12/5
 */
public class ReadExcel {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String question;
        String filePath;
        List<String> answer = new ArrayList<>();
        while (true) {

            //获取用户输入的题目：
            question = scanner.nextLine();


            filePath = "D:\\IdeaProject space\\video\\src\\main\\resources\\企业财务报表分析.xlsx";

            File file = new File(filePath);
            Workbook wb = null;
            //判断文件是否存在
            try {
                if (file.exists()) {
                    String[] split = file.getName().split("\\.");  //.是特殊字符，需要转义！！！！！

                    //根据文件后缀进行判断
                    if (split[1].equals("xls")) {
                        //获取文件输出流
                        FileInputStream fileInputStream = new FileInputStream(file);
                        wb = new HSSFWorkbook(fileInputStream);
                    } else if (split[1].equals("xlsx")) {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        wb = new XSSFWorkbook(fileInputStream);
                    } else {
                        System.out.println("文件类型错误！");
                    }
                    //开始解析Excel单元格
                    Sheet sheet = wb.getSheetAt(0);

                    //获取第一行，是列名，所以不读
                    int firstRowIndex = sheet.getFirstRowNum() + 1;
                    //最后一行
                    int lastRowNum = sheet.getLastRowNum();

                    //System.out.println("firstRowIndex = " + firstRowIndex);
                    //System.out.println("lastRowNum = " + lastRowNum);

                    //遍历
                    for (int rIndex = firstRowIndex; rIndex <= lastRowNum; rIndex++) {
                        //System.out.println("当前第 " + rIndex + " 行");

                        Row row = sheet.getRow(rIndex);

                        //获取行数据，如果行数据不为空
                        if (row != null) {
                            int firstCellNum = row.getFirstCellNum();
                            int lastCellNum = row.getLastCellNum();

                            //遍历列
                            for (int cIndex = firstCellNum; cIndex < lastCellNum; cIndex++) {
                                Cell cell = row.getCell(cIndex);
                                if (cell != null) {
//                                    System.out.println(cell.toString());
                                    if (cell.toString().contains(question)) {
                                        System.out.println("文档中第 " + (rIndex + 1) + "行，答案为：\r\n" + cell.toString());
                                        answer.add(rIndex+"");
                                    }
                                }

                            }

                        }

                    }
                    if(answer.size() > 1){
                        System.out.println("检索到多个，请仔细确认题目");
                    }

                } else {
                    System.out.println("文件不存在！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
