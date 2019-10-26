package org.vl.example.rest.dtofromoaramsandpath.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MyCriteriaLd {
    private LocalDate from;
    private LocalDate till;
    private Long communityNumber;
    private String communityName;
}
