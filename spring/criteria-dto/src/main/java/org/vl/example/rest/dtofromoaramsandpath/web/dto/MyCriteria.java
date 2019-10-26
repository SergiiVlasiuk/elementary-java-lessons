package org.vl.example.rest.dtofromoaramsandpath.web.dto;

import lombok.Data;

@Data
public class MyCriteria {
    private String from;
    private String till;
    private Long communityNumber;
    private String communityName;
}
