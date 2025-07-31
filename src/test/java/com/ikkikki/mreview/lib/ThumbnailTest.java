package com.ikkikki.mreview.lib;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ThumbnailTest {


  @Test // 원본 이미지의 썸네일.webp 변환
  public void testConvert() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\workspaces_phj\\img 업로드 테스트 샘플", "ab766a21-ab0c-4f67-b492-aeb0e3680b2f.webp"));
    BufferedImage thumbnail = Thumbnails.of(originalImage)
            .size(200,200)
            .asBufferedImage();
    ImageIO.write(thumbnail, "webp", new File("C:\\Users\\tj\\workspaces_phj\\img 업로드 테스트 샘플", "ab766a21-ab0c-4f67-b492-aeb0e3680b2f_output.webp"));
  }

  @Test // 원본 이미지의 해상도 유지. 포맷만 webp로 변환
  public void testConvertOnly() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\workspaces_phj\\img 업로드 테스트 샘플", "87f6f272-3503-409f-a77d-256296c6c9c1.png"));
    ImageIO.write(originalImage, "webp", new File("C:\\Users\\tj\\workspaces_phj\\img 업로드 테스트 샘플", "87f6f272-3503-409f-a77d-256296c6c9c1_output.webp"));
  }

  @Test
  public void testConvert2() throws IOException {

  }


}
