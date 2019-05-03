package com.example.image;

import com.example.image.mapper.DemoMapper;
import com.example.image.model.RGBModel;
import com.example.image.util.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageApplicationTests {


    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void contextLoads() {
        readfile("C:\\Users\\DELL\\Desktop\\翰联400色色卡_512x300px\\renamed_fig");
    }

    private boolean readfile(String filepath) {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
                addFile(file);
            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());
                        addFile(readfile);
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

    private void addFile(File file) throws IOException {
        String fileName = file.getName();
        File toPic = new File(getUUID()+".jpg");
        // 压缩
        ImageUtil.thumbnails(file, toPic);
        // 计算像素值
        int[] imagePixel = ImageUtil.getImagePixel(toPic);
        RGBModel rgbModel = new RGBModel(file.getPath().replaceAll("\\\\", "/"), imagePixel[0], imagePixel[1], imagePixel[2]);
        toPic.delete();
        demoMapper.add(rgbModel);
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
