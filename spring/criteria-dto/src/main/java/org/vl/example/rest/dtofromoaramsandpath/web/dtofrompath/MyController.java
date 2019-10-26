package org.vl.example.rest.dtofromoaramsandpath.web.dtofrompath;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.vl.example.rest.dtofromoaramsandpath.web.dto.MyCriteria;

import java.time.LocalDate;

@RestController
@RequestMapping("verify/criteria/mapping")
@Slf4j
public class MyController {

    @GetMapping("community/{communityNumber}/dto")
    public MyCriteria loadDataByDto(MyCriteria criteria) {
        log.info("received criteria: {}", criteria);
        return criteria;
    }

    @GetMapping("community/{communityNumber}/default/params")
    public String loadDataByDefaultParameters(@PathVariable("communityNumber") String communityNumber,
                                              @RequestParam(value = "from", required = false) String from,
                                              @RequestParam(value = "till", required = false) String till,
                                              @RequestParam(value = "communityName", required = false) String communityName) {
        log.info("received data without converting:\n\tcommunityNumber => {}\n\tfrom => {}\n\ttill => {}\n\tcommunityName => {}",
                communityNumber, from, till, communityName);
        return new StringBuilder("{")
                .append("\n\tfrom:").append(from).append(";")
                .append("\n\tfrom:").append(from).append(";")
                .append("\n\ttill:").append(till).append(";")
                .append("\n\tcommunityName:").append(communityName)
                .append("\n}\n").toString();
    }

    @GetMapping("community/{communityNumber}/converted/params")
    public String loadUsingConvertedParameters(@PathVariable("communityNumber") String communityNumber,
                                               @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                               @RequestParam("till") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate till,
                                               @RequestParam(value = "communityName", required = false) String communityName) {
        log.info("received data with LocalDate converting:\n\tcommunityNumber => {}\n\tfrom => {}\n\ttill => {}\n\tcommunityName => {}",
                communityNumber, from, till, communityName);
        return new StringBuilder("{")
                .append("\n\tfrom:").append(from).append(";")
                .append("\n\tfrom:").append(from).append(";")
                .append("\n\ttill:").append(till).append(";")
                .append("\n\tcommunityName:").append(communityName)
                .append("\n}\n").toString();
    }
}
