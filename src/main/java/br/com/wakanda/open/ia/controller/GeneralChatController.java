package br.com.wakanda.open.ia.controller;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class GeneralChatController {

    private final OpenAiChatClient chatClient;

    // Pergunta Geral
    @GetMapping("/general")
    public String generalChat(@RequestParam(value = "query", required = true) String query) {
        String response = chatClient.call(query);
        return response;
    }
    
    // informações sobre um tópico específico
    @GetMapping("/info")
    public String specificInfo(@RequestParam(value = "topic", required = true) String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Diga-me cinco fatos interessantes sobre {topic}.
                """);
        promptTemplate.add("topic", topic);

        return chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }
}
