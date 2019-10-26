package org.vl.example.rest.dtofromoaramsandpath.web.dtofrompath;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vl.example.rest.dtofromoaramsandpath.web.dto.MyCriteriaLd;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("verify/criteria/mapping")
@Slf4j
public class MyControllerLd {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE));
            }
        });
    }

    @GetMapping("community/{communityNumber}/dtold")
    public MyCriteriaLd loadDataByDto(MyCriteriaLd criteria) {
        log.info("received criteria: {}", criteria);
        return criteria;
    }
}
