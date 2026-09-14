package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.DashboardResponse;
import com.rahul.rexptrack.service.AnalyticsService;
import com.rahul.rexptrack.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;
    private final SecurityUtil securityUtil;

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<DashboardResponse>> dashboard() {
        return ResponseEntity.ok(ApiResponse.success("Dashboard retrieved", analyticsService.getDashboard(securityUtil.getCurrentUserId())));
    }

    public AnalyticsController(AnalyticsService analyticsService, SecurityUtil securityUtil) {
        this.analyticsService = analyticsService;
        this.securityUtil = securityUtil;
    }
}


