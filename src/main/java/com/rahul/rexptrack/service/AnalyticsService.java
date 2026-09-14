package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.response.DashboardResponse;

public interface AnalyticsService {
    DashboardResponse getDashboard(Long userId);
}
