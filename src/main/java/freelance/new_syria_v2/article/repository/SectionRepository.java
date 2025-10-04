package freelance.new_syria_v2.article.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.article.entity.Section;

public interface SectionRepository extends JpaRepository<Section, UUID> {

}
