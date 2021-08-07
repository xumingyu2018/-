package com.xmy.demophoto.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传控制器
 * @author 胥明毓
 * @date 2021/05/25 21:46
 **/
@RestController
public class UploadController {
    @PostMapping("/cover")
    public String cover(@RequestBody MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            File dir = new File("public");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String name = "public/" + UUID.randomUUID() + file.getOriginalFilename();
            FileOutputStream outputStream = new FileOutputStream(name);
            byte[] bytes = file.getBytes();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return "http://127.0.0.1:3001/" + name;
        }
        return "";
    }

    @GetMapping("/public/{name}")
    public void getCover(@PathVariable("name") String name, HttpServletResponse resp) throws IOException {
        File file = new File("public/" + name);
        if (file.exists()) {
            ServletOutputStream outputStream = resp.getOutputStream();
            FileInputStream inputStream = new FileInputStream(file);
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();
        }
    }
}
