import entity.Commodity;
import entity.Good;
import impl.CommodityImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Bonnie on 2022/1/11
 */
public class commodityTest {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        commodityTest t = new commodityTest();
        // 指定excel路径
        File file = new File("D:\\NetBeansProjects\\commodity_settlement_system\\src\\main\\resources\\商品数据.xlsx");
        List<Good> goods = t.readExcel(file);
        Commodity commodity = new Commodity();

        commodity.setGoods(goods);
        CommodityImpl.add(commodity);
    }

    public List<Good> readExcel(File file) throws IOException, InvalidFormatException {
        List<Good> goods = new ArrayList<>();

        if( file.isFile() && file.exists() )
        {
            String[] split = file.getName().split("\\.");
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            if ( "xls".equals(split[1])){
                FileInputStream fis = new FileInputStream(file);   //文件流对象
                wb = new HSSFWorkbook(fis);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(file);
            }else {
                System.out.println("文件类型错误!");
                return null;
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();

            for( int i = firstRowIndex; i <= lastRowIndex; ++i )
            {
                Row row = sheet.getRow(i);
                if( null != row )
                {
                    Good good = new Good();
                    good.setName(row.getCell( 0).toString());
                    good.setPicture(new FileInputStream(row.getCell(1).toString()));
                    good.setCost(new BigDecimal(row.getCell(2).toString()));
                    good.setInventory((int)Double.parseDouble(row.getCell(3).toString()));
                    goods.add(good);
//                    System.out.println(good.getName() + " - " + good.getCost() + " - " + good.getInventory());
                }
            }
        }else {
            System.out.println("找不到指定的文件");
        }
        return goods;
    }
}
