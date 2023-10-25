package com.example.bookstore.controller;

import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgUtil;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/img")
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {
    @PostMapping("/{img_name}")
    public void findImg(@PathVariable("img_name") String img_name, @NotNull javax.servlet.http.HttpServletResponse response) throws IOException {
        if (img_name.equals("null")) {
            return;
        }
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/";
        File file = new File(path + img_name);
        BufferedImage image = ImageIO.read(file);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        response.setHeader("content-type", "image/png");
    }

    @PostMapping(value = "/upload")
    public Msg uploadImg(@RequestParam MultipartFile file) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "static";
        String fname = file.getOriginalFilename();//获取文件名称
        assert fname != null;
        String suffixName = fname.substring(fname.lastIndexOf("."));//获取文件后缀
        fname = UUID.randomUUID() + suffixName;//重新生成文件名
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File saveFile = new File(targetFile, fname);
        String filePath;
        try {
            //指定本地存入路径
            file.transferTo(saveFile);
            filePath = path + "/" + fname;
        } catch (Exception e) {
            e.printStackTrace();
            return MsgUtil.makeMsg(MsgUtil.UPLOAD_ERROR, "上传失败");
        }
        // build a JSON object as response
        JSONObject resp = new JSONObject();
        resp.put("path", fname);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, "上传成功", resp);
    }
}
