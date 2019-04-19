package com.ctzn.springangularsandbox.controllers;

import com.ctzn.springangularsandbox.components.email.FeedbackSender;
import com.ctzn.springangularsandbox.dto.FeedbackDTO;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path = "api/feedback")
public class FeedbackController {

    private FeedbackSender feedbackSender;

    private Logger logger;

    public FeedbackController(FeedbackSender feedbackSender, Logger logger) {
        this.feedbackSender = feedbackSender;
        this.logger = logger;
    }

    @PostMapping
    public void sendFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        logger.info("Sending feedback: " + feedbackDTO);
        feedbackSender.send(feedbackDTO);
    }
}
