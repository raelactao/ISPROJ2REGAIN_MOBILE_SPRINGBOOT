package com.isproj2.regainmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GreenZoneDTO {
    private int id;
    private String title;
    private String link;
    private String summary;
    private LocalDate date;
}
