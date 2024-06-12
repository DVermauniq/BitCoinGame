package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.entity.Story;
import com.rwl.Bit_coin.serviceImplementation.StoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/status")
public class StoryController {

    @Autowired
    private StoryServiceImpl statusService;

    @PostMapping("/upload")
    public ResponseEntity<Story> uploadStatus(@RequestParam Long userId, @RequestParam("file") MultipartFile file) {
        try {
            Story uploadedStatus = statusService.uploadStatus(userId, file);
            return ResponseEntity.ok(uploadedStatus);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOldStatuses() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        statusService.deleteByCreatedAtBefore(cutoff);
        return ResponseEntity.ok("Old statuses cleaned up successfully.");
    }
}
