package com.junseong.webservice.web;

import com.junseong.webservice.domain.posts.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostRepository postRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postRepository.save(dto.toEntity());
    }
}
