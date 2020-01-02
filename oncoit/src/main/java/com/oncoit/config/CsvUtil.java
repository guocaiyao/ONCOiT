package com.oncoit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import org.apache.commons.io.IOUtils;

/**
 * Author: gyao
 * Date: 12/29/19
 * E-mail: yaoguocai_cool@163.com
 **/
public class CsvUtil {
    private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);
    public static byte[] getBytesByDateMap(
            String[] headers, List<Map<String,String>> datas){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeCsvByDataMap(outputStream,headers,datas);
        return outputStream.toByteArray();
    }

    public static void writeCsvByDataMap(
            OutputStream out,String[] headers,List<Map<String,String>> datas
    ){
        OutputStreamWriter outputStream = new OutputStreamWriter(out);
        ICsvMapWriter writer = new CsvMapWriter(outputStream, CsvPreference.EXCEL_PREFERENCE);

        try {
            writer.writeHeader(headers);
            for(Map<String,String> item:datas){
                writer.write(item,headers);
            }
        }catch (IOException e){
            logger.error("create csv file error!");
        }finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
