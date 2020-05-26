package com.junseong.webservice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        postRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("junseongday@gmail.com")
                .build());
        //when
        final List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));

    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        final LocalDateTime now = LocalDateTime.now();
        postRepository.save(Posts.builder()
        .title("test title")
        .content("test content")
        .author("test author")
        .build());
        //when
        final List<Posts> postsList = postRepository.findAll();
        final Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}