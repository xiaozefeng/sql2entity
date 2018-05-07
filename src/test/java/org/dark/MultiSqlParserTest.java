package org.dark;

import org.junit.Test;

import java.util.Arrays;

/**
 * 多个建表语句
 *
 * @author xiaozefeng
 * @date 2018/5/7 上午10:59
 */
public class MultiSqlParserTest {

    private String sql = "CREATE TABLE `TB_HXB_LOAN_REPAYMENT` (\n" +
            "  `ID` varchar(36) NOT NULL,\n" +
            "  `AGENT_ID` varchar(50) DEFAULT NULL,\n" +
            "  `CAP_REQUEST_NO` varchar(50) DEFAULT NULL,\n" +
            "  `HXB_LOAN_ID` varchar(50) NOT NULL,\n" +
            "  `INTEREST` decimal(15,2) DEFAULT NULL,\n" +
            "  `LOAN_ACCT_CODE` varchar(255) DEFAULT NULL,\n" +
            "  `ORDER_ID` varchar(50) DEFAULT NULL,\n" +
            "  `REPAYAMOUNT` decimal(15,2) DEFAULT NULL,\n" +
            "  `REPAYDATE` datetime DEFAULT NULL,\n" +
            "  `STATUS` varchar(30) NOT NULL,\n" +
            "  `AMOUNTINTEREST` decimal(15,2) NOT NULL,\n" +
            "  `AMOUNTOUTSTANDING` decimal(15,2) NOT NULL,\n" +
            "  `AMOUNTPRINCIPAL` decimal(15,2) NOT NULL,\n" +
            "  `DUEDATE` varchar(20) NOT NULL,\n" +
            "  `VALUEDATE` datetime NOT NULL,\n" +
            "  `TRIAL_STATUS` varchar(30) DEFAULT NULL,\n" +
            "  `FAIL_REASON` varchar(30) DEFAULT NULL,\n" +
            "  `REPAY_STATUS` varchar(30) DEFAULT NULL,\n" +
            "  `REPAYMENT_DATE` datetime DEFAULT NULL,\n" +
            "  `LOAN_TIME` datetime DEFAULT NULL,\n" +
            "  `TAX_CANCEL` tinyint(1) NOT NULL DEFAULT '0',\n" +
            "  PRIMARY KEY (`ID`),\n" +
            "  KEY `INDEX_TB_HXB_LOAN_REPAYMENT_HXB_LOAN_ID` (`HXB_LOAN_ID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "CREATE TABLE `TB_HXB_LOAN_ORDER` (\n" +
            "  `ID` varchar(36) NOT NULL,\n" +
            "  `LOAN_END_DATE` datetime DEFAULT NULL,\n" +
            "  `CAP_REQUEST_NO` varchar(255) NOT NULL,\n" +
            "  `FAIL_REASON` varchar(255) DEFAULT NULL,\n" +
            "  `HXB_LOAN_ID` varchar(255) NOT NULL,\n" +
            "  `LOAN_ACCT_CODE` varchar(255) DEFAULT NULL,\n" +
            "  `LOAN_AGREEMENT_CODE` varchar(255) DEFAULT NULL,\n" +
            "  `LOAN_AMOUNT` decimal(15,2) DEFAULT NULL,\n" +
            "  `LOAN_TIME` datetime DEFAULT NULL,\n" +
            "  `ORDER_DATE` varchar(255) NOT NULL,\n" +
            "  `ORDER_ID` varchar(255) NOT NULL,\n" +
            "  `STAT` varchar(255) NOT NULL,\n" +
            "  `TIMERECORDED` datetime NOT NULL,\n" +
            "  PRIMARY KEY (`ID`),\n" +
            "  KEY `INDEX_TB_HXB_LOAN_ORDER_ORDER_ID` (`ORDER_ID`) USING BTREE,\n" +
            "  KEY `INDEX_TB_HXB_LOAN_ORDER_HXB_LOAN_ID` (`HXB_LOAN_ID`) USING BTREE\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private String sql2 = "CREATE TABLE `RF_AUTOBID_REPAYMETHOD` (\n" +
            "  `USER_ID` varchar(255) DEFAULT NULL,\n" +
            "  `REPAYMENT_METHOD` varchar(255) NOT NULL,\n" +
            "  KEY `FK_RF_AUTOBID_REPAYMETHOD_USER_ID` (`USER_ID`),\n" +
            "  CONSTRAINT `FK_RF_AUTOBID_REPAYMETHOD_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `TB_USER_AUTO_BID` (`ID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    @Test
    public void testParse(){
        String[] split = sql.split("CHARSET=utf8;");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));
    }
}
