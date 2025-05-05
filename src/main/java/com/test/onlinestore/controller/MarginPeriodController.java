package com.test.onlinestore.controller;

import com.test.onlinestore.model.MarginPeriod;
import com.test.onlinestore.service.MarginPeriodService;
import com.test.onlinestore.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/margin-periods")
@RequiredArgsConstructor
public class MarginPeriodController {

    private final MarginPeriodService marginPeriodService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MarginPeriod>>> getAll() {
        var marginPeriod = marginPeriodService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Margin Period fetched successfully", marginPeriod));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MarginPeriod>> getById(@PathVariable Long id) {
        var marginPeriod = marginPeriodService.findById(id);
        if (marginPeriod == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Margin Period not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Margin Period found", marginPeriod));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MarginPeriod>> create(@RequestBody MarginPeriod data) {
        var marginPeriod = marginPeriodService.save(data);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Margin Period created", marginPeriod));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MarginPeriod>> update(@PathVariable Long id, @RequestBody MarginPeriod data) {
        var marginPeriod = marginPeriodService.updateMarginPeriod(id, data);
        if (marginPeriod == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Margin Period not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Margin Period updated", marginPeriod));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        marginPeriodService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Margin Period deleted", null));

    }
}

