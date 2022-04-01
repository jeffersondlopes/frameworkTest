package br.com.framework.frameworkpost.config.kafka;

import br.com.framework.frameworkpost.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClient {

    private final KafkaTemplate<String, User> kafkaTemplate;

    private static final String USER_TOPIC_NAME = "PHOTO_USER";

    public void sendMessage(User user){
        kafkaTemplate.send(USER_TOPIC_NAME, user);
    }

}
