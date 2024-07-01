package jp.colworks.credit_manage_server.application.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.colworks.credit_manage_server.application.form.LoginForm;
import jp.colworks.credit_manage_server.domain.model.LoginModel;
import jp.colworks.credit_manage_server.domain.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログイン認証を行うコントローラークラス
 * 
 * @author col
 */
@RestController
public class AuthController {

    @Autowired
    private LoginService service;

    @GetMapping("/api/authenticate")
    public String authenticate(@RequestBody LoginForm loginForm) {

        LoginModel model = LoginModel.builder()
                .username(loginForm.getUsername())
                .password(loginForm.getPassword()).build();

        return service.authenticate(model);
    }
}
