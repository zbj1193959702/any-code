package com.hainiu.cat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * create by biji.zhao on 2020/12/28
 */
@Controller
public class FileController {

    @GetMapping(value = "/file")
    public String file(){
        return "file";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file, Model model, HttpServletRequest request) {

//        String fileName = file.getOriginalFilename();
//        try {
//            byte[] bytes = file.getBytes();
//
//            File parentFile = new File("/photo");
//
//            if (!parentFile.exists()) {
//                parentFile.mkdirs();
//            }
//            File dest = new File(parentFile, fileName);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(dest);
//
//            fileOutputStream.write(bytes);
//        }catch (Exception e) {
//
//        }

        String fileName = file.getOriginalFilename(); // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
        String filePath = "D://photo//";
        fileName = UUID.randomUUID() + suffixName;

        File dest = new File(filePath + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存文件，此方法必须是已经存在的路径，否则会报错
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/photo/" + fileName;
        model.addAttribute("filename", filename);
        return "file";
    }


}
