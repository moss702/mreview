package com.ikkikki.mreview.controller;

import com.ikkikki.mreview.domain.dto.UploadResultDTO;
import com.ikkikki.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;
  public final static String UPLOAD_PATH = "d:/upload/files";

  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files)  throws IOException{

    List<UploadResultDTO> list = new ArrayList<>();

    for(MultipartFile f : files){
      //-------------- 이미지만 업로드 가능
      if (!f.getContentType().startsWith("image/")){
        log.warn(f.getContentType() + " is not supported");
        return ResponseEntity.badRequest().body("wrong content type. 잘못된 파일 형식입니다.");
      }
      String uuid = UUID.randomUUID().toString();
      String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
      String savePath = uploadPath + "/" + path;
      // 실제 파일의 정보를 가져옴

      new File(savePath).mkdirs();
      
      // 원본 파일 webp 변환
//      String origin = f.getOriginalFilename();
//      origin = origin.substring(0, origin.lastIndexOf('.')) + ".webp";
      String origin = f.getOriginalFilename().substring(0, f.getOriginalFilename().lastIndexOf('.')) + ".webp";
      String saveName =  uuid + "_" + origin;
      ImageIO.write(ImageIO.read(f.getInputStream()), "webp", new File(savePath, saveName));;
      
      // 썸네일 생성
      String thumbName = "s_" + saveName;
      BufferedImage thumbnail = Thumbnails.of(ImageIO.read(f.getInputStream()))
              .size(200,200)
              .asBufferedImage();
      ImageIO.write(thumbnail, "webp", new File(savePath, thumbName));;
      
      // 응답 목록 추가
      list.add(UploadResultDTO.builder().origin(origin).uuid(uuid).path(path).build());
      }
      return ResponseEntity.ok(list);
  }

  @GetMapping("display")
  public ResponseEntity<?> display(UploadResultDTO dto) throws IOException {
    File file = new File(uploadPath + "/" + dto.getPath(), dto.getUuid() + "_" + dto.getOrigin());
    HttpHeaders  headers = new HttpHeaders();
    headers.add("Content-Type", Files.probeContentType(file.toPath()));
    return ResponseEntity.ok().headers(headers).body(Files.readAllBytes(file.toPath()));
  }   // 교재 401P


  @GetMapping("uploadEx")
  public void uploadEx(){

  }
}
