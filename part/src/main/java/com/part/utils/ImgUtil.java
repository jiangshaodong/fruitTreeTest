package com.part.utils;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * @author jiangSD
 * @since 2019-10-23
 */
public class ImgUtil {
    /*public static final String ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com";
    public static final String ACCESSKEYID = "LTAIeC5SACKCP1qB";
    public static final String ACCESSKEYSECRET = "8m1CXxIhb0zL0RXXIFgzqLYm6UVx5x";
    public static final String BUCKETNAME = "fruittree";
    public static final String URL="http://fruittree.oss-cn-shenzhen.aliyuncs.com/image/";*/

    public static final String ENDPOINT = "http://oss-cn-chengdu.aliyuncs.com";
    public static final String ACCESSKEYID = "LTAI4FoHwyskjPc5VBvNCpFH";
    public static final String ACCESSKEYSECRET = "v2hWe4hSlNEfT2nOCyj5VzPXQWfjja";
    public static final String BUCKETNAME = "xhcwlkjyxgs";
    public static final String URL="http://xhcwlkjyxgs.oss-cn-chengdu.aliyuncs.com/image/";
    public static String GenerateImage(String imgStr,String imgFilePath)
    {   //对字节数组字符串进行Base64解码并生成图片
       /* if (imgStr == null) //图像数据为空
            return null;
        Pattern p = Pattern.compile("data:image/(.*?);base64,");
        Matcher m = p.matcher(imgStr);
        String suffix = null;
        if(m.find()) {
            suffix = m.group(1);
        }
        imgStr = imgStr.replaceAll("data:image/(.*?);base64,","");*/
        String suffix = "png";
        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath+"."+suffix);
            out.write(b);
            out.flush();
            out.close();
            System.out.println(imgFilePath.substring(imgFilePath.lastIndexOf("/")+1)+"."+suffix);
            return imgFilePath.substring(imgFilePath.lastIndexOf("/")+1)+"."+suffix;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 上传图片 nginx
     * @param
     */
    public static List<String> Upload(String[] files){
        List<String> fileList = new ArrayList<>();
        try {
            for (String file:
                    files) {
                String path = ImgUtil.GenerateImage(file.split(",")[1], "E:/nginx-1.12.1/img/" + System.currentTimeMillis());
                fileList.add(path);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileList;
    }


    /**
     * oss
     * @param files
     * @return
     */
    public static List<String> Uploads(String[] files){
        List<String> fileList = new ArrayList<>();
        try {
            for (String file:
                    files) {
                //String path = ImgUtil.GenerateImage(file.split(",")[1], "E:/nginx-1.12.1/img/" + System.currentTimeMillis());

                //////////////////////
                String suffix = "png";

                //Base64解码
                byte[] b = Base64.decodeBase64(file.replaceAll("data:image/(.*?);base64,",""));
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                //生成jpeg图片
                // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
                // 创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件
                String key = System.currentTimeMillis()+".png";

                PutObjectResult putObject = ossClient.putObject(BUCKETNAME, "image/"+key, new ByteArrayInputStream(b));
                System.out.println(putObject.getETag());
                fileList.add(key);
                // 关闭client
                ossClient.shutdown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileList;
    }
    public static List<String> Uploads(MultipartFile[] files, boolean isUrl){
        List<String> fileList = new ArrayList<>();
        try {
            for (MultipartFile file:
                    files) {
                byte[] b = file.getBytes();
                //生成jpeg图片
                // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
                // 创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件
                String key = System.currentTimeMillis()+".png";

                PutObjectResult putObject = ossClient.putObject(BUCKETNAME, "image/"+key, new ByteArrayInputStream(b));
                System.out.println(putObject.getETag());
                if(isUrl){
                    fileList.add("http://xhcwlkjyxgs.oss-cn-chengdu.aliyuncs.com/image/"+key);
                }else {
                    fileList.add(key);
                }
                // 关闭client
                ossClient.shutdown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileList;
    }
    public static List<String> UploadsNon(MultipartFile[] files, boolean isUrl){
        List<String> fileList = new ArrayList<>();
        try {
            for (MultipartFile file:
                    files) {
                byte[] b = file.getBytes();
                //生成jpeg图片
                // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
                // 创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件
                String key = System.currentTimeMillis()+".png";

                PutObjectResult putObject = ossClient.putObject(BUCKETNAME, "image/"+key, new ByteArrayInputStream(b));
                System.out.println(putObject.getETag());
                if(isUrl){
                    fileList.add(key);
                }else {
                    fileList.add(key);
                }
                // 关闭client
                ossClient.shutdown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileList;
    }
    public static List<String> uploadsForHead(MultipartFile[] files, boolean isUrl, String hxPhone){
        List<String> fileList = new ArrayList<>();
        try {
            for (MultipartFile file:
                    files) {
                byte[] b = file.getBytes();
                //生成jpeg图片
                // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
                // 创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件
                String key =hxPhone+".png";

                PutObjectResult putObject = ossClient.putObject(BUCKETNAME, "image/"+key, new ByteArrayInputStream(b));
                System.out.println(putObject.getETag());
                if(isUrl){
                    fileList.add("http://xhcwlkjyxgs.oss-cn-beijing.aliyuncs.com/image/"+key);
                }else {
                    fileList.add(key);
                }
                // 关闭client
                ossClient.shutdown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileList;
    }
}
