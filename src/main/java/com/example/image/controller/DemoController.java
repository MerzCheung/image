package com.example.image.controller;

import com.example.image.mapper.DemoMapper;
import com.example.image.model.RGBModel;
import com.example.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoMapper demoMapper;

    @PostMapping("/selectImage")
    public Object selectImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        final File excelFile = File.createTempFile(getUUID(), prefix);
        file.transferTo(excelFile);
        File toPic = new File(getUUID()+".jpg");
        // 压缩
        ImageUtil.thumbnails(excelFile, toPic);
        // 计算像素值
        int[] imagePixel = ImageUtil.getImagePixel(toPic);
        excelFile.delete();
        toPic.delete();
        return demoMapper.selectImage(imagePixel[0], imagePixel[1], imagePixel[2]);
    }

    @PostMapping("/addImage")
    public void addImage(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String prefix=fileName.substring(fileName.lastIndexOf("."));
            final File excelFile = File.createTempFile(getUUID(), prefix);
            file.transferTo(excelFile);
            File toPic = new File(getUUID()+".jpg");
            // 压缩
            ImageUtil.thumbnails(excelFile, toPic);
            // 计算像素值
            int[] imagePixel = ImageUtil.getImagePixel(toPic);
            RGBModel rgbModel = new RGBModel(fileName, imagePixel[0], imagePixel[1], imagePixel[2]);
            excelFile.delete();
            toPic.delete();
            demoMapper.add(rgbModel);
        }
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
