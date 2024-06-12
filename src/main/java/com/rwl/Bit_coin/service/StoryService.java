package com.rwl.Bit_coin.service;



import org.springframework.web.multipart.MultipartFile;

import com.rwl.Bit_coin.entity.Story;

import io.jsonwebtoken.io.IOException;

public interface StoryService {

    Story uploadStatus(Long userId, MultipartFile file) throws IOException, java.io.IOException;
	

}
