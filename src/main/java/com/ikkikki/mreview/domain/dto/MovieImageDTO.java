package com.ikkikki.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDTO {
  private String origin;
  private String uuid;
  private String path;
  private Long mno; // fk - movie number

  public String getUrl(){ // map.of
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

  public String getThumb(){ // map.of
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", "s_" + uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }

}
