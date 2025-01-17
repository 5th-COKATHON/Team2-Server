package com.cotato.team2.team2.controller.dto;

import java.util.List;
import lombok.Data;

@Data
public class ChatGPTResponse {

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    @Data
    public static class Choice {
        private int index;
        private Message message;
        private String finishReason;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
        private String refusal;
    }

    @Data
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
        private PromptTokensDetails promptTokensDetails;
        private CompletionTokensDetails completionTokensDetails;

        @Data
        public static class PromptTokensDetails {
            private int cachedTokens;
            private int audioTokens;
        }

        @Data
        public static class CompletionTokensDetails {
            private int reasoningTokens;
            private int audioTokens;
            private int acceptedPredictionTokens;
            private int rejectedPredictionTokens;
        }
    }
}
