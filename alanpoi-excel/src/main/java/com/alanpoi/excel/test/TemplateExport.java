package com.alanpoi.excel.test;

import com.alanpoi.excel.ExportVO;
import com.alanpoi.excel.exports.handle.TemplateHandle;
import com.alanpoi.excel.parse.ZipPackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TemplateExport {

    public static void main(String[] args) throws IOException {
        TemplateHandle templateHandle = new TemplateHandle();
        List<ContractVO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContractVO contractVO = new ContractVO();
            contractVO.setCustomerName("XXX科技服务有限公司");
            contractVO.setCustomerAddress("广东省深圳市XXXX科技城B2栋");
            contractVO.setCustomerTel("0755-XXXXXX");
            contractVO.setCompanyName("Alanpoi");
            contractVO.setCompanyAddress("北京市XXXX");
            contractVO.setCompanyTel("0755XXXXXX");
            contractVO.setContractAmount(new BigDecimal("10000"+i));
            contractVO.setDiscountAmount(new BigDecimal("500"+i));
            contractVO.setPageUrl("https://alanpoi.com");
            list.add(contractVO);
        }
        long begin = System.currentTimeMillis();
        ZipPackage zipPackage = templateHandle.getExcelStream("/Users/pengzhuoxun/Downloads/合同模版.xlsx", list, ContractVO.class, -1);
        System.out.println("alanpoi 总耗时 " + (System.currentTimeMillis() - begin) + "毫秒");
        zipPackage.write(new FileOutputStream("/Users/pengzhuoxun/Downloads/合同.xlsx"));
    }
}
