package com.rwl.Bit_coin.AwsS3Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {

	private final String awsAccessKey = "your-access-key";
	private final String awsSecretKey = "your-secret-key";
	private final String awsRegion = "us-east-1"; // Change to your AWS region
	private final String awsS3Bucket = "your-bucket-name";

	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
						"https://s3." + awsRegion + ".amazonaws.com", awsRegion))
				.build();
	}

	public String getAwsS3Bucket() {
		return awsS3Bucket;
	}
}
