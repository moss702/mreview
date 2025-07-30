package com.ikkikki.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Controller
@Log4j2
public class UploadController {
  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files){
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {
      log.info(f.getOriginalFilename());
      try {
        f.transferTo(new File(":/upload", f.getOriginalFilename()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("fileName", f.getOriginalFilename(), "size", f.getSize());
    }).toList());
  }

  @GetMapping("uploadEx")
  public void uploadEx(){

  }
}
