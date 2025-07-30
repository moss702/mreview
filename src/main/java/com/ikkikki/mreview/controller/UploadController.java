package com.ikkikki.mreview.controller;

import com.ikkikki.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;
  public final static String UPLOAD_PATH = "d:/upload/files";



  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files){
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {

      String uuid = null;
      String folderPath = null;
      try {
        //-------------- 이미지만 업로드 가능
        if(!f.getContentType().startsWith("image/")) {
          log.warn(f.getContentType() + " is not supported");
          return ResponseEntity.badRequest().build();
        }
        log.info(f.getOriginalFilename());
        folderPath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        uuid = UUID.randomUUID().toString();
        folderPath = uploadPath + "/" + folderPath;
        String saveName =  uuid + "_" + f.getOriginalFilename();

        new File(folderPath).mkdirs();

        // ------------파일 저장
        f.transferTo(new File(folderPath, saveName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("origin", f.getOriginalFilename(), "size", f.getSize(), "uuid", uuid, "path", folderPath);
    }).toList());
  }

  @GetMapping("uploadEx")
  public void uploadEx(){

  }
}
