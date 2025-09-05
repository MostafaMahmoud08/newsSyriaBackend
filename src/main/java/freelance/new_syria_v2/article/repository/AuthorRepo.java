package freelance.new_syria_v2.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.article.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, String> {

	
}

/*
  post api/v1/authors "convert from normal user to author" 
  

 */