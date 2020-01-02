package com.oncoit.config;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: gyao
 * Date: 12/28/19
 * E-mail: yaoguocai_cool@163.com
 **/

public abstract class BaseController {
    protected Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    protected void downloadFile(String fileName,byte[] fileBytes,String contentType){
        ServletOutputStream outputStream=null;
        try {
            outputStream=response.getOutputStream();
            response.setContentType(contentType);
            response.setContentLength(fileBytes.length);
            response.addHeader("Content-Disposition","attachment;filename=".concat(fileName));
            IOUtils.write(fileBytes,outputStream);
        }catch (IOException e){
            logger.error("Download File Fail!",e);
        }finally {
            IOUtils.closeQuietly(outputStream);
        }
    }
}
