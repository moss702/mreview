package com.ikkikki.mreview.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "movie")
public class MovieImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long inum;
  private String uuid;
  private String imgName;
  private String path;

  @ManyToOne(fetch = FetchType.LAZY)
  private Movie movie;

  private boolean present;

}
