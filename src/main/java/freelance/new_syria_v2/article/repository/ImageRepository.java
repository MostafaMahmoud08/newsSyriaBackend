package freelance.new_syria_v2.article.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.article.entity.Image;

public interface ImageRepository extends JpaRepository<Image, UUID> {

}
