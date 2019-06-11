package com.jawnho.douyuspringboot.util;

import java.io.*;

/**
 * 文本读写
 */
public class FileUtil {

    /**
     * 读文件
     */
    public static String readFile(String pathname) {
        String content = "";
        try (
                FileReader reader = new FileReader(pathname);
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 写文件
     */
    public static void writeFile(String pathname, String content) {
        try {
            File file = new File(pathname);
            file.createNewFile();
            try (
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(content);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
