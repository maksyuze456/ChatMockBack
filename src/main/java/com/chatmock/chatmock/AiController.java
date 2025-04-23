package com.chatmock.chatmock;

import java.util.Map;
import org.json.*;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
public class AiController {
    @Autowired
    ChatModel chatModel;

    @PostMapping("/joke")
    public ResponseEntity<String> tellJoke(@RequestBody Map<String, String> body) {
        
        // JSONObject obj = new JSONObject(body);
        // String input = obj.getString("prompt");
        
        String input = body.get("prompt");
        Prompt prompt = new Prompt(input);
        ChatResponse response = chatModel.call(prompt);
        return new ResponseEntity<String>(response.getResult().getOutput().getText(), HttpStatus.OK);
    }
}
