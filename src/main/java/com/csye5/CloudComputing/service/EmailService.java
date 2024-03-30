package com.csye5.CloudComputing.service;

import com.csye5.CloudComputing.controller.DbHealthController;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class EmailService {
    private final String projectId=System.getenv("GCP_PROJECT_ID");
    private final String topicId=System.getenv("TOPIC_ID");
    private Publisher publisher;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @PostConstruct
    public void initialize() throws IOException {
        // Create a Pub/Sub publisher
        try {
            TopicName topicName = TopicName.of(projectId, topicId);
            publisher = Publisher.newBuilder(topicName).build();
        } catch (Exception e) {
            logger.error("Error initializing publisher: " + e.getMessage());
        }
    }
    public void sendEmail(String email) {
        try {
            TopicName topicName = TopicName.of(projectId, topicId);
            publisher = Publisher.newBuilder(topicName).build();
            ByteString data = ByteString.copyFromUtf8(email);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
            publisher.publish(pubsubMessage);
            logger.info("Email sent: " + email);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage());
        }
    }
}
