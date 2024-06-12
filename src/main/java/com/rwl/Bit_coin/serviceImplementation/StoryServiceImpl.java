package com.rwl.Bit_coin.serviceImplementation;

import java.time.LocalDateTime;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rwl.Bit_coin.AwsS3Config.AwsS3Config;
import com.rwl.Bit_coin.entity.Story;
import com.rwl.Bit_coin.repo.StoryRepository;
import com.rwl.Bit_coin.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository statusRepo;

    @Autowired
    private AwsS3Config awsConfig;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public Story uploadStatus(Long userId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        try {
            // Generate unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Set metadata with content length
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            // Upload file to S3 bucket
            amazonS3.putObject(awsConfig.getAwsS3Bucket(), fileName, file.getInputStream(), metadata);

            // Construct S3 URL
            String fileUrl = amazonS3.getUrl(awsConfig.getAwsS3Bucket(), fileName).toString();

            // Save status to the database
            Story status = new Story();
      //      status.setUserId(userId);
            status.setImageUrl(fileUrl);
            status.setCreatedAt(LocalDateTime.now());  // Assuming Story has a createdAt field
            return statusRepo.save(status);
        } catch (AmazonServiceException | IOException e) {
            throw new IOException("Error occurred while uploading file to S3", e);
        }
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void removeOldStatuses() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        deleteByCreatedAtBefore(cutoff);
    }

    public void deleteByCreatedAtBefore(LocalDateTime cutoff) {
        statusRepo.deleteByCreatedAtBefore(cutoff);
    }
}
