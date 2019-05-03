package com.example.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author merz
 * @Description:
 * @date 2019/5/3 23:12
 */
@Controller
public class SelectController {

    @GetMapping("/{page}")
    public String select(@PathVariable String page) {
        return page;
    }


    /**
     * IO流读取图片 by:long
     * @return
     */
    @GetMapping(value = "/IoReadImage")
    public String IoReadImage(String imgName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            ips = new FileInputStream(new File(imgName));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }
}
