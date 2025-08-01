package com.ikkikki.mreview.controller;

import com.ikkikki.mreview.domain.dto.MovieDTO;
import com.ikkikki.mreview.domain.dto.PageRequestDTO;
import com.ikkikki.mreview.domain.dto.PageResponseDTO;
import com.ikkikki.mreview.service.MovieService;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Log4j2
@Builder
@Controller
@RequestMapping("movie")
public class MovieController {
  private final MovieService movieService;
  
  @GetMapping("register")
  public void  register(){
  }
  @PostMapping("register")
  public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
    log.info(movieDTO);
    redirectAttributes.addFlashAttribute("msg", movieService.register(movieDTO));
    return "redirect:/movie/register";
  }

  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model){
//    PageResponseDTO<?,?> Pagedto = movieService.getList(dto);
//    log.info(Pagedto);
    model.addAttribute("movies", movieService.getList(dto));
  }

  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long mno){
    log.info(mno);
    model.addAttribute("dto", movieService.get(mno));

  }
  
}
