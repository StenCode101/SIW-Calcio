package it.uniroma3.siw.siw_calcio.controller;

package com.tuoprogetto.siwcalcio.controller;

import com.tuoprogetto.siwcalcio.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping("/tornei")
    public String mostraTornei(Model model) {
        model.addAttribute("tornei", torneoService.trovaTutti());
        return "tornei";
    }
}