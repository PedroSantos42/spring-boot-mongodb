package com.pedrosantos.workshopmongo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.pedrosantos.workshopmongo.services.exceptions.ObjectNotFoundException;

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

	@Test
	public void givenInvalidArgs_FindById_ReturnObjectNotFoundException() {
		given(postRepository.findById("123")).willReturn(mockOptionalOfPost());

		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			postService.findById("321");
		});

		assertNotNull(exception);
		assertEquals("Objeto não encontrado", exception.getMessage());
	}

	@Test
	public void givenValidArgs_FindByTitle_ReturnListOfPosts() {
		given(postRepository.findByTitleContainingIgnoreCase(Mockito.any(String.class))).willReturn(mockPostList());

		List<Post> posts = postService.findByTitle("Game of Thrones");

		assertNotNull(posts);
	}

	@Test
	public void givenValidArgs_FullSearch_ReturnListOfPosts() {
		given(postRepository.findByTitleContainingIgnoreCase(Mockito.any(String.class))).willReturn(mockPostList());

		List<Post> posts = postService.fullSearch("feliz", LocalDate.of(2018, 03, 20), LocalDate.of(2018, 03, 24));

		assertNotNull(posts);
	}

	private List<Post> mockPostList() {
		Optional<Post> post1 = mockOptionalOfPost();
		post1.get().setId("111");
		post1.get().setTitle("Game of Thrones");

		Optional<Post> post2 = mockOptionalOfPost();
		post2.get().setId("222");
		post2.get().setTitle("Book of Five Rings");

		Optional<Post> post3 = mockOptionalOfPost();
		post3.get().setId("333");
		post3.get().setTitle("Harry Potter");

		List<Post> posts = new ArrayList<>();

		posts.add(post1.get());
		posts.add(post2.get());
		posts.add(post3.get());

		return posts;
	}

	private Optional<Post> mockOptionalOfPost() {
		Post post = new Post();

		AuthorDTO author = new AuthorDTO();

		author.setId("123abc123abc123abc");
		author.setName("John Doe");

		List<CommentDTO> comments = new ArrayList<>();

		comments.add(new CommentDTO("Comentário 1", LocalDate.of(2020, 2, 17), author));
		comments.add(new CommentDTO("Comentário 2", LocalDate.of(2018, 4, 27), author));

		post.setId("abc123-edf456-ghi789");
		post.setDate(LocalDate.of(2020, 11, 12));
		post.setTitle("Ipsum Lorem");
		post.setBody("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
		post.setAuthor(author);
//		post.setComments(comments);

		return Optional.of(post);
	}
}