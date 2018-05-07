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

    private String sql = "CREATE TABLE `TB_COOP_INFO` (\n" +
            "  `ID` varchar(36) NOT NULL,\n" +
            "  `CODE` varchar(255) DEFAULT NULL,\n" +
            "  `NAME` varchar(255) DEFAULT NULL,\n" +
            "  `PRIKEYPATH` varchar(255) DEFAULT NULL,\n" +
            "  `PUBKEYPATH` varchar(255) DEFAULT NULL,\n" +
            "  `URL` varchar(255) DEFAULT NULL,\n" +
            "  `USER_KEY` varchar(256) NOT NULL COMMENT '第三方平台提供的接口凭证',\n" +
            "  `DESCRIPTION` varchar(255) NOT NULL DEFAULT '' COMMENT '第三方平台描述信息',\n" +
            "  PRIMARY KEY (`ID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "CREATE TABLE `TB_COOP_LOG` (\n" +
            "  `ID` varchar(36) NOT NULL,\n" +
            "  `COOP_CODE` varchar(10) NOT NULL COMMENT '合作平台',\n" +
            "  `CORPORATION_NAME` varchar(64) NOT NULL COMMENT '企业名',\n" +
            "  `ORG_CODE` varchar(32) NOT NULL COMMENT '企业code',\n" +
            "  `USER_ID` varchar(32) NOT NULL COMMENT '用户id',\n" +
            "  `OPERATION_TYPE` varchar(12) NOT NULL COMMENT '操作类型',\n" +
            "  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',\n" +
            "  `REMARK` varchar(256) DEFAULT '' COMMENT '备注',\n" +
            "  PRIMARY KEY (`ID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private String sql2 = "CREATE TABLE `RF_AUTOBID_REPAYMETHOD` (\n" +
            "  `USER_ID` varchar(255) DEFAULT NULL,\n" +
            "  `REPAYMENT_METHOD` varchar(255) NOT NULL,\n" +
            "  KEY `FK_RF_AUTOBID_REPAYMETHOD_USER_ID` (`USER_ID`),\n" +
            "  CONSTRAINT `FK_RF_AUTOBID_REPAYMETHOD_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `TB_USER_AUTO_BID` (`ID`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    @Test
    public void testParse(){
        String[] split = sql2.split(";\n");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));
    }
}
