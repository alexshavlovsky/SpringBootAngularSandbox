package com.ctzn.mynotesservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontendEnvironmentController {
    @Value("${app.auth.username}")
    private String userName;

    @Value("${app.auth.password}")
    private String userPassword;

    @Value("${app.api.path}")
    private String apiBasePath;

    // this controller will override default frontend client
    // environment properties stored in /static/env.js
    @GetMapping(value = "/env.js", produces = "application/javascript")

    public String overrideEnvironment() {
        return String.format("(function (window) {" +
                "window.__env = window.__env || {};" +
                "window.__env.apiEndpoint = '%s';" +
                "window.__env.apiAuth = '%s:%s'" +
                "}(this));", apiBasePath, userName, userPassword);
    }
}