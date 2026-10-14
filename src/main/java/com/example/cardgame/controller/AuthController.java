package com.example.cardgame.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardgame.model.User;
import com.example.cardgame.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 新規ユーザー登録 API
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 入力チェック (nullおよび空文字判定)
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "ユーザー名とパスワードを入力してください。"));
        }

        // ユーザー名の重複チェック
        if (userRepository.existsByUsername(username.trim())) {
            return ResponseEntity.badRequest().body(Map.of("message", "そのユーザー名は既に使用されています。"));
        }

        // 新規ユーザーの保存
        User newUser = new User(username.trim(), password);
        User savedUser = userRepository.save(newUser);

        return ResponseEntity.ok(Map.of(
            "message", "ユーザー登録が完了しました！",
            "user", savedUser
        ));
    }

    /**
     * ログイン API
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "ユーザー名とパスワードを入力してください。"));
        }

        // Optionalを利用して安全にユーザー取得
        Optional<User> userOptional = userRepository.findByUsername(username.trim());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "ユーザー名またはパスワードが正しくありません。"));
        }

        User user = userOptional.get();

        // パスワードの検証
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "ユーザー名またはパスワードが正しくありません。"));
        }

        // ログイン成功時にクライアントへ返却
        return ResponseEntity.ok(Map.of(
            "message", "ログイン成功",
            "user", user
        ));
    }
}