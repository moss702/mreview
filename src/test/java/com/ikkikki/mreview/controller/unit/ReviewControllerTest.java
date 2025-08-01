package com.ikkikki.mreview.controller.unit;
import com.ikkikki.mreview.controller.ReviewController;
import com.ikkikki.mreview.repository.ReviewRepository;
import com.ikkikki.mreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// 단위 테스트는 @SpringBootTest 안씀!
@WebMvcTest(ReviewController.class)
@ContextConfiguration(name = "application.yml")
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewServiceImpl service;
  @MockBean
  private ReviewRepository repository;


  @Test
  public void 단순목록조회() throws Exception {
    Long mno = 101L;
    mockMvc.perform(get(String.format("/reviews/%d/all", mno)));
  }

}
