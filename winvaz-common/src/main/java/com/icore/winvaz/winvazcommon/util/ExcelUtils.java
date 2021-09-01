package com.icore.winvaz.winvazcommon.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel工具类
 * @Author wdq
 * @Create 2021/5/27 18:31
 * @Version 1.0.0
 */

public class ExcelUtils<T> {

    /**
     * 标题
     */
    private String title;

    /**
     * 单元格宽度
     */
    private int colWith = 10;

    /**
     * 行高度
     */
    private int rowWith = 10;

    /**
     * HSSF
     */
    private HSSFWorkbook hssfWorkbook;

    /**
     * 表头样式
     */
    private HSSFCellStyle headStyle;

    /**
     * 主体样式
     */
    private HSSFCellStyle bodyStyle;


}
