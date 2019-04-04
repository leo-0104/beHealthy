package com.example.demo.controller;

import com.example.demo.util.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;


@RestController
public class UtilController {

    @PostMapping("/uploadImage/{id}/{fileName}")
    public String upload(@RequestPart("uploadFile") MultipartFile uploadFile, @PathVariable("id") Integer id,@PathVariable("fileName") String fileName) {
        //判断文件是否为空
        if (uploadFile.isEmpty()) {
            return JsonResult.failed(-1,"文件为空");
        }
        try {
            //获取输入流对象
            InputStream inputStream = uploadFile.getInputStream();
            String filePath = "E:\\image\\personPhoto\\";

            FileOutputStream fileOutputStream = new FileOutputStream(filePath + new File(fileName));
            //读取和写入信息
            int len = -1;
            //创建一个字节数组，当做缓冲区
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, bytes.length);
                //要将缓冲区的数据推入磁盘
                fileOutputStream.flush();
            }
            if (null != fileOutputStream) {
                fileOutputStream.close();
            }
            if (null != inputStream) {
                inputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failed(-2,"上传文件失败");
        }
        return JsonResult.success();
    }
}
