package com.part.controller.sys;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.part.entity.vo.EditResult;
import com.part.utils.ImgUtil;
import com.part.utils.Result;

import java.util.List;


/**
 * Created by JiangSD on 2019/10/18.
 */
@Api(tags = {"api_imgUpload"})
@RestController
public class UploadController {

    @RequestMapping("/upload")
    public EditResult multipleSave(@RequestParam MultipartFile[] files){
        EditResult result = new EditResult();
        try {
            List<String> fileList = ImgUtil.Uploads(files,true);
            result.setErrno(0);
            result.setData(fileList);
        }catch (Exception e){
            e.printStackTrace();
            result.setErrno(1);
        }
        return result;
    }
    @RequestMapping("/uploads")
    public Result multipleSave2(@RequestParam MultipartFile[] file){
        List<String> fileList = ImgUtil.Uploads(file,false);
        return new Result().success(fileList);
    }
    @RequestMapping("/uploadsForEditor")
    public Result uploadsForEditor(@RequestParam MultipartFile[] files){
        List<String> fileList = ImgUtil.Uploads(files,true);
        return new Result().success(fileList);
    }
    @RequestMapping("/uploadsForHead" )
    public Result uploadsForHead(@RequestParam MultipartFile[] file, String hxPhone){
        List<String> fileList = ImgUtil.uploadsForHead(file,false,hxPhone);
        return new Result().success(fileList);
    }
}
