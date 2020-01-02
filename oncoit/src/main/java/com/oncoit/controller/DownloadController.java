package com.oncoit.controller;

import com.oncoit.config.BaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: gyao
 * Date: 12/29/19
 * E-mail: yaoguocai_cool@163.com
 **/
@RestController
@RequestMapping("/download")
public class DownloadController extends BaseController {
    @GetMapping("/oncoit_core_table.csv")
    public void textFile(){
        ServletOutputStream outputStream=null;
        String servletPath=request.getServletPath();
        String fileName=servletPath.substring(servletPath.lastIndexOf("/")+1);
        try {
            InputStream fileResource=this.getClass().getResourceAsStream(
                    "/data"+servletPath);
            byte[] txtBytes=IOUtils.toByteArray(fileResource);
            outputStream=response.getOutputStream();
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setContentLength(txtBytes.length);
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            IOUtils.write(txtBytes,outputStream);
        }catch (IOException e){
            logger.error("Download txt File Fail!",e);
        }finally {
            IOUtils.closeQuietly(outputStream);
        }
    }
}
