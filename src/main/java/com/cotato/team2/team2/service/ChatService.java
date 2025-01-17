package com.cotato.team2.team2.service;

import com.cotato.team2.team2.controller.dto.ChatGPTRequest;
import com.cotato.team2.team2.controller.dto.ChatGPTResponse;
import com.cotato.team2.team2.controller.dto.ChatResponse;
import com.cotato.team2.team2.controller.dto.GPTResponse;
import com.cotato.team2.team2.domain.entity.User;
import com.cotato.team2.team2.service.component.UserCommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    private final UserCommonService userCommonService;

	private final RestTemplate restTemplate;

    public ChatResponse chat(String sessionKey, String message) {
        User user = userCommonService.findBySessionKey(sessionKey);
        GPTResponse response = chat(message);
        return new ChatResponse(response.message());
    }

    public GPTResponse chat(String prompt) {
        String processedPrompt = """
			You're a chatbot talking to the user. You should answer all their questions and conversations in a slightly sarcastic and rude manner, but still be tsundere.
			
				                                                                                 
			Here is the user message: 
			""" + prompt + """
			
			Respond should be in korean.
			Respond in the following JSON format:
															 
			    {\s
					"message": "Okay, what's bothering you? Go ahead and talk. I might help if there's something I can do, but don't get your hopes up."\s
				}"
				 
			""";

        ChatGPTRequest request = new ChatGPTRequest(model, processedPrompt);

        ChatGPTResponse gptResponse = restTemplate.postForObject(apiURL, request, ChatGPTResponse.class);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("GPT Response: {}", gptResponse);
        try {
            return objectMapper.readValue(gptResponse.getChoices().get(0).getMessage().getContent(),
                    GPTResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse GPT response", e);
        }
    }
}
