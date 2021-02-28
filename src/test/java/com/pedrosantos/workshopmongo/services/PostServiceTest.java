package com.pedrosantos.workshopmongo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pedrosantos.workshopmongo.domain.Post;
import com.pedrosantos.workshopmongo.dto.AuthorDTO;
import com.pedrosantos.workshopmongo.dto.CommentDTO;
import com.pedrosantos.workshopmongo.repository.PostRepository;

@ExtendWith(SpringExtension.class)
class PostServiceTest {

	@Mock
	private PostRepository postRepository;

	@InjectMocks
	private PostService postService;

	@Test
	public void givenValidArgs_FindById_ReturnPostInstance() {
		given(postRepository.findById(Mockito.any(String.class))).willReturn(mockOptionalOfPost());

		Post post = postService.findById("123");

		assertNotNull(post);
	}

	private Optional<Post> mockOptionalOfPost() {
		Post post = new Post();

		AuthorDTO author = new AuthorDTO();

		author.setId("123abc123abc123abc");
		author.setName("John Doe");

		List<CommentDTO> comments = new ArrayList<>();

		comments.add(new CommentDTO("Comentário 1", new Date(654321987L), author));
		comments.add(new CommentDTO("Comentário 2", new Date(321456123L), author));

		post.setId("abc123-edf456-ghi789");
		post.setDate(new Date(459459459L));
		post.setTitle("Ipsum Lorem");
		post.setBody("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		post.setAuthor(author);
		post.setComments(comments);

		return Optional.of(post);
	}
}