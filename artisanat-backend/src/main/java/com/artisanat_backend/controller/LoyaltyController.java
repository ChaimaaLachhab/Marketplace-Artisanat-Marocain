package com.artisanat_backend.controller;

import com.artisanat_backend.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loyalties")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyService;

}
