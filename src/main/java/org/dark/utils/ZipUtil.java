package org.dark.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip 工具类
 *
 * @author xiaozefeng
 * @date 2018/5/7 下午6:50
 */
public class ZipUtil {

    /**
     * 压缩
     * @param inFile
     * @param zipOutputStream
     */
    public static void doZip(File inFile, ZipOutputStream zipOutputStream) throws IOException {
        FileInputStream fis = null;
        try {
            ZipEntry zipEntry = new ZipEntry(inFile.getName());
            zipOutputStream.putNextEntry(zipEntry);
            int len = 0;
            byte[] buffer = new byte[1024];

            fis = new FileInputStream(inFile);
            while ((len = fis.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
                zipOutputStream.flush();
            }
        } finally {
            zipOutputStream.closeEntry();
            if (fis != null) {
                fis.close();
            }
        }
    }
}
