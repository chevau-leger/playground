package com.lvd.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExcelUtil {

    private static List<String> kpi_title = new ArrayList<>(Arrays.asList("id", "kpi_id", "sche_type", "obj_code", "func_domain_code",
            "initiator_system_code", "landing_system_code", "biz_code", "interface_code", "link_code", "province_code",
            "ability_code", "middle_desk", "original_interface_code", "scale", "enable"));

    private static List<String> time_title = new ArrayList<>(Arrays.asList("id", "mg_kpi_id",
            "begin_time", "end_time", "max_value", "min_value", "time_flag", "enable"));

    private static List<String> kpIds = new ArrayList<>(Arrays.asList("PM-ZHZT280-OTHER-01-001-000", "PM-ZHZT280-OTHER-01-002-000", "PM-ZHZT280-OTHER-01-003-000", "PM-ZHZT280-OTHER-01-004-000", "PM-ZHZT280-OTHER-01-005-000", "PM-ZHZT280-OTHER-01-006-000", "PM-ZHZT280-OTHER-01-007-000", "PM-ZHZT280-OTHER-01-008-000", "PM-ZHZT280-OTHER-01-009-000", "PM-ZHZT280-OTHER-01-010-000", "PM-ZHZT280-OTHER-07-001-000", "PM-ZHZT280-OTHER-07-002-000", "PM-ZHZT280-OTHER-07-003-000"));

    private static List<String> initiatorSystemCode = new ArrayList<>(Arrays.asList("SC001001", "SC001001", "SC001001", "ZHZT280", "ZHZT280", "ZHZT280", "ZHZT280", "YWZT280", "YWZT280", "YWZT280", "YWZT280", "ZHZT280", "ZHZT280"));

    private static List<String> landingSystemCode = new ArrayList<>(Arrays.asList("ZHZT280", "ZHZT280", "ZHZT280", "YWZT280", "YWZT280", "YWZT280", "YWZT280", "ZHZT280", "ZHZT280", "ZHZT280", "ZHZT280", "SC001001", "SC001001"));

    private static List<String> linkCode = new ArrayList<>(Arrays.asList("001", "001", "001", "002", "002", "002", "002", "003", "003", "003", "003", "004", "004"));

    private static List<String> scale = new ArrayList<>(Arrays.asList("0", "0", "2", "0", "0", "0", "2", "0", "0", "0", "2", "0", "0"));

    private static List<String> beginTime = new ArrayList<>(Arrays.asList("0", "22", "8", "0", "0", "22", "8", "0", "0", "0", "22", "8", "0", "0", "0", "22", "8", "0", "0", "0", "0"));

    private static List<String> endTime = new ArrayList<>(Arrays.asList("7", "24", "21", "24", "7", "24", "21", "24", "24", "7", "24", "21", "24", "24", "7", "24", "21", "24", "24", "24", "24"));

    private static List<String> maxValue = new ArrayList<>(Arrays.asList("", "", "", "0", "100", "100", "100000", "0", "0", "100", "100", "100000", "0", "0", "100", "100", "100000", "0", "0.5", "0.5", "0.5"));

    private static List<String> minValue = new ArrayList<>(Arrays.asList("", "", "", "0", "0", "0", "100", "0", "0", "0", "100", "0", "0", "0", "0", "0", "100", "0", "0.1", "0.1", "0.1"));

    private static List<String> timeFlag = new ArrayList<>(Arrays.asList("2", "2", "1", "0", "2", "2", "1", "0", "0", "2", "2", "1", "0", "0", "2", "2", "1", "0", "0", "0", "0"));


    private static List<String> certainInterfaceTop = new ArrayList<>(Arrays.asList(
            "Grid_modifyGridWorkOrderStatus",
            "Market_realtimeMatchingPush",
            "Order_fusionOrderAccept",
            "processWoQuery"
    ));

    private static List<String> certainInterfaceMiddle = new ArrayList<>(Arrays.asList(
            "Grid_createGridWorkOrder",
            "Order_calcFee",
            "Order_checkOrder",
            "Order_orderIntegratedGoods"
    ));

    private static List<String> certainInterfaceBottom = new ArrayList<>(Arrays.asList(
            "Cust_orderDeal",
            "Grid_queryAgent",
            "Grid_queryGridWorkOrderInfo",
            "Mgt_contractItemInfoQuery",
            "Prod_smartHomeGoodsQuery"
    ));


    public static void main(String[] args) throws Exception {

        List<InterfaceInfo> interfaceInfos = getInterfaceInfos();
        write(interfaceInfos);
    }

    private static List<InterfaceInfo> getInterfaceInfos() throws Exception {

        InputStream fileInputStream = Files.newInputStream(Paths.get("C:\\Users\\echo\\Desktop\\kpi.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        List<InterfaceInfo> interfaceInfos = new ArrayList<>();
        XSSFSheet sheet = workbook.getSheetAt(2);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            XSSFRow row = sheet.getRow(i);

            InterfaceInfo interfaceInfo = new InterfaceInfo();

            XSSFCell bizCode = row.getCell(0);
            bizCode.setCellType(CellType.STRING);
            interfaceInfo.setBizCode(bizCode.getStringCellValue());

            XSSFCell abilityCode = row.getCell(1);
            abilityCode.setCellType(CellType.STRING);
            interfaceInfo.setAbilityCode(abilityCode.getStringCellValue());

            XSSFCell interfaceCode = row.getCell(2);
            interfaceCode.setCellType(CellType.STRING);
            interfaceInfo.setInterfaceCode(interfaceCode.getStringCellValue());

            XSSFCell originalInterfaceCode = row.getCell(3);
            originalInterfaceCode.setCellType(CellType.STRING);
            interfaceInfo.setOriginalInterfaceCode(originalInterfaceCode.getStringCellValue().replaceAll("\r|\n", ""));

            interfaceInfos.add(interfaceInfo);
        }
        return interfaceInfos;
    }

    private static Map<Integer, List<KpiValueRange>> getKpiValueRange() throws Exception {

        InputStream fileInputStream = Files.newInputStream(Paths.get("C:\\Users\\echo\\Desktop\\kpi.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet = workbook.getSheetAt(4);

        Map<Integer, List<KpiValueRange>> result = new HashMap<>();
        List<KpiValueRange> kpiValueRanges = new ArrayList<>();

        int count = 1;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {

            KpiValueRange kpiValueRange = new KpiValueRange();
            XSSFRow row = sheet.getRow(i);
            XSSFCell beginTime = row.getCell(2);
            beginTime.setCellType(CellType.STRING);
            kpiValueRange.setBeginTime(beginTime.getStringCellValue());

            XSSFCell endTime = row.getCell(3);
            endTime.setCellType(CellType.STRING);
            kpiValueRange.setEndTime(endTime.getStringCellValue());

            XSSFCell maxValue = row.getCell(4);
            maxValue.setCellType(CellType.STRING);
            kpiValueRange.setMaxValue(maxValue.getStringCellValue());

            XSSFCell minValue = row.getCell(5);
            minValue.setCellType(CellType.STRING);
            kpiValueRange.setMinValue(minValue.getStringCellValue());
            kpiValueRanges.add(kpiValueRange);

            if ((i + 1) % 3 == 0) {

                result.put(count, kpiValueRanges);
                kpiValueRanges = new ArrayList<>();
                count++;
            }
        }
        return result;
    }

    private static void write(List<InterfaceInfo> interfaceInfos) {

        String path = "C:\\Users\\echo\\Desktop\\newKpi.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet newKpiSheet = workbook.createSheet("newKpi");
        XSSFSheet periodTimeSheet = workbook.createSheet("periodTime");
        composeTitle(newKpiSheet, kpi_title);
        composeTitle(periodTimeSheet, time_title);

        for (int i = 0; i < interfaceInfos.size(); i++) {

            for (int j = i * 13 + 1; j <= (i + 1) * 13; j++) {

                XSSFRow row = newKpiSheet.createRow(j);
                for (int k = 0; k < kpi_title.size(); k++) {

                    row.createCell(k);
                }
            }
            for (int j = i * 21 + 1; j <= (i + 1) * 21; j++) {

                XSSFRow row = periodTimeSheet.createRow(j);
                for (int k = 0; k < time_title.size(); k++) {

                    row.createCell(k);
                }
            }
        }

        int periodTimeCount = 0;
        for (int i = 0; i < interfaceInfos.size(); i++) {

            InterfaceInfo interfaceInfo = interfaceInfos.get(i);
            int timeCountInLoop = -1;
            for (int j = i * 13 + 1; j <= (i + 1) * 13; j++) {

                int currentInLoop = j - i * 13 - 1;
                String originalInterfaceCode = interfaceInfo.getOriginalInterfaceCode();

                newKpiSheet.getRow(j).getCell(0).setCellValue(572 + j);
                newKpiSheet.getRow(j).getCell(1).setCellValue(kpIds.get(currentInLoop));
                newKpiSheet.getRow(j).getCell(2).setCellValue("15MI");
                newKpiSheet.getRow(j).getCell(3).setCellValue("ZHZT280");
                newKpiSheet.getRow(j).getCell(4).setCellValue("OTHER");
                newKpiSheet.getRow(j).getCell(5).setCellValue(initiatorSystemCode.get(currentInLoop));
                newKpiSheet.getRow(j).getCell(6).setCellValue(landingSystemCode.get(currentInLoop));
                newKpiSheet.getRow(j).getCell(7).setCellValue(interfaceInfo.getBizCode());
                newKpiSheet.getRow(j).getCell(8).setCellValue(interfaceInfo.getInterfaceCode());
                newKpiSheet.getRow(j).getCell(9).setCellValue(linkCode.get(currentInLoop));
                newKpiSheet.getRow(j).getCell(10).setCellValue("280");
                newKpiSheet.getRow(j).getCell(11).setCellValue(interfaceInfo.getAbilityCode());
                newKpiSheet.getRow(j).getCell(12).setCellValue("YWZT");
                newKpiSheet.getRow(j).getCell(13).setCellValue(originalInterfaceCode);
                newKpiSheet.getRow(j).getCell(14).setCellValue(scale.get(currentInLoop));
                newKpiSheet.getRow(j).getCell(15).setCellValue("1");


                if (certainInterfaceTop.contains(originalInterfaceCode)) {

                    setServiceInvocations("0", "2", "50", "100", "500", "1000");
                } else if (certainInterfaceMiddle.contains(originalInterfaceCode)) {

                    setServiceInvocations("0", "2", "2", "10", "20", "100");
                } else if (certainInterfaceBottom.contains(originalInterfaceCode)) {

                    setServiceInvocations("0", "1", "1", "5", "10", "20");
                } else {

                    setServiceInvocations("0", "0", "1", "3", "0", "10");
                }

                if (currentInLoop == 1 || currentInLoop == 3 || currentInLoop == 4 || currentInLoop == 6 || currentInLoop == 7 ||
                        currentInLoop == 9 || currentInLoop == 10 || currentInLoop == 11 || currentInLoop == 12) {

                    periodTimeCount++;
                    timeCountInLoop++;
                    setPeriodTimeTable(periodTimeSheet, periodTimeCount, timeCountInLoop, 572 + j);
                } else if (currentInLoop == 0 || currentInLoop == 2 || currentInLoop == 5 || currentInLoop == 8) {

                    for (int k = 0; k < 3; k++) {

                        periodTimeCount++;
                        timeCountInLoop++;
                        setPeriodTimeTable(periodTimeSheet, periodTimeCount, timeCountInLoop, 572 + j);
                    }
                }
            }
        }

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setServiceInvocations(String zero, String seven, String twentyTwo, String twentyFour, String eight, String twentyOne) {

        //0-7
        minValue.set(0, zero);
        maxValue.set(0, seven);
        //22-24
        minValue.set(1, twentyTwo);
        maxValue.set(1, twentyFour);
        //8-21
        minValue.set(2, eight);
        maxValue.set(2, twentyOne);
    }

    private static void setPeriodTimeTable(XSSFSheet periodTimeSheet, int rowCount, int countInLoop, int kpiId) {

        periodTimeSheet.getRow(rowCount).getCell(0).setCellValue(UUID.randomUUID().toString().replace("-", ""));
        periodTimeSheet.getRow(rowCount).getCell(1).setCellValue(kpiId);
        periodTimeSheet.getRow(rowCount).getCell(2).setCellValue(beginTime.get(countInLoop));
        periodTimeSheet.getRow(rowCount).getCell(3).setCellValue(endTime.get(countInLoop));
        periodTimeSheet.getRow(rowCount).getCell(4).setCellValue(maxValue.get(countInLoop));
        periodTimeSheet.getRow(rowCount).getCell(5).setCellValue(minValue.get(countInLoop));
        periodTimeSheet.getRow(rowCount).getCell(6).setCellValue(timeFlag.get(countInLoop));
        periodTimeSheet.getRow(rowCount).getCell(7).setCellValue("1");
    }

    private static void composeTitle(XSSFSheet sheet, List<String> title) {

        XSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {

            XSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellValue(title.get(i));
        }
    }
}
