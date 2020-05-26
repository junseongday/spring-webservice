package com.junseong.webservice.service;

import com.junseong.webservice.domain.posts.Posts;
import com.junseong.webservice.domain.posts.PostsRepository;
import com.junseong.webservice.dto.posts.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }


    @Test
    public void Dto데이터가_posts테이블에_저장된다() {
        // given when
        final PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("junseong")
                .content("test")
                .title("test title")
                .build();
        final Long id = postsService.save(dto);

        //then
        final List<Posts> byId = postsRepository.findAllById(Collections.singleton(id));
        final Posts posts = byId.get(0);
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
    }
}