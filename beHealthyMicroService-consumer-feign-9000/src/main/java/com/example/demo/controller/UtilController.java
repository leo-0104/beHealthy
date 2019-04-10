package com.example.demo.controller;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import com.example.demo.entity.MedicalAuscultation;
import com.example.demo.service.MedicalAuscultationService;
import com.example.demo.util.JsonResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class UtilController {
    public static final String APP_ID = "10118987";
    public static final String API_KEY = "f9c21zGI0TiHcgcaVYMaVDYp";
    public static final String SECRET_KEY = "KcYw0Vw5Kw7TQ51KP1vSfI95gnsHCsoE";

    @Autowired
    private MedicalAuscultationService medicalAuscultationService;
    @PostMapping("/uploadImage/{id}")
    public String upload(@RequestPart("uploadFile") MultipartFile uploadFile, @PathVariable("id") Integer id) {
        //判断文件是否为空
        if (uploadFile.isEmpty()) {
            return JsonResult.failed(-1,"文件为空");
        }
        try {
            //获取输入流对象
            InputStream inputStream = uploadFile.getInputStream();
            String filePath = "E:\\image\\personPhoto\\";
            if(id == 2){
                filePath = "E:\\image\\medicalRecordPhoto\\";
            }else if (id == 3){
                filePath = "E:\\image\\memberPhoto\\";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + uploadFile.getOriginalFilename());
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


    @PostMapping("/uploadVideo/{uid}")
    public String uploadVideo(@RequestPart("uploadFile") MultipartFile uploadFile, @PathVariable("uid") Integer uid) {
        //判断文件是否为空
        if (uploadFile.isEmpty()) {
            return JsonResult.failed(-1,"文件为空");
        }
        try {
            //获取输入流对象
            InputStream inputStream = uploadFile.getInputStream();
            String filePath = "E:\\record\\";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + uploadFile.getOriginalFilename());
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

            //语音识别音频文件
            String info = transToText(filePath + uploadFile.getOriginalFilename());
            if (info != null){
                MedicalAuscultation medicalAuscultation = new MedicalAuscultation();
                //用户id
                medicalAuscultation.setUid(uid);
                //地址
                medicalAuscultation.setVideoUrl(filePath + uploadFile.getOriginalFilename());
                //操作时间
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                medicalAuscultation.setOperTime(simpleDateFormat.format(date));
                //内容
                medicalAuscultation.setContent(info);
                     //将数据持久化到MySQL中
             medicalAuscultationService.addMedicalAus(medicalAuscultation);
            }else {
                return JsonResult.failed(-1,"文字识别失败");
            }


        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failed(-2,"上传文件失败");
        }
        return JsonResult.success();
    }


    @PostMapping("/uploadImage")
    public String uploadNewsCover(@RequestParam("file")MultipartFile uploadFile) {
        //判断文件是否为空
        if (uploadFile.isEmpty()) {
            return JsonResult.failed(-1,"文件为空");
        }
        try {
            //获取输入流对象
            InputStream inputStream = uploadFile.getInputStream();
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(),"static/healthNews/");
            if(!upload.exists()) upload.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(upload.getAbsoluteFile() + "/" + uploadFile.getOriginalFilename());
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
        return JsonResult.success("healthNews/" + uploadFile.getOriginalFilename());
    }

    /**
     * 语音识别音频文件
     * @param path
     * @return
     */
    public String transToText(String path){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        byte[] bytes = new byte[0];
        try {
            bytes = Util.readFileByBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //  client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        //对语音二进制数据进行识别，保存至m4a格式
        JSONObject res = client.asr(bytes, "m4a", 16000, null);

        //提取识别成功之后的文本
        JSONArray content = null;
        try {
            content = res.getJSONArray("result");
            StringBuilder sb = new StringBuilder();
            for (int index = 0 ;index < content.length();index++){
                sb.append(content.get(index));
            }
            return sb.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
