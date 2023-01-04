//package com.myCompany.RepairAgency.servlet.util.report.writer.writer;
//import com.myCompany.RepairAgency.servlet.util.report.writer.XLSWriter;
//import org.junit.jupiter.api.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class XLSWriterTest {
//    private static final String FILE_NAME = "temp.xlsx";
//    private String fileLocation;
//
//    @BeforeAll
//    public void generateExcelFile() throws IOException {
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        fileLocation = path.substring(0, path.length() - 1) + FILE_NAME;
//
////        XLSWriter.createReport();
//    }
//
//    @Test
//    public void whenParsingPOIExcelFile_thenCorrect() throws IOException {
////        Map<Integer, List<String>> data
////                = excelPOIHelper.readExcel(fileLocation);
////
////        assertEquals("Name", data.get(0).get(0));
////        assertEquals("Age", data.get(0).get(1));
////
////        assertEquals("John Smith", data.get(1).get(0));
////        assertEquals("20", data.get(1).get(1));
//    }
//}
