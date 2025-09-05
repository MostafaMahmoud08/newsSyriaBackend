package freelance.new_syria_v2.article.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@OneToMany(mappedBy = "article")
	List<Section>sections;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "thumbnail_id",referencedColumnName = "id")
	private Image thumbnail;
	
	private String header;

	@ManyToOne()
	@JoinColumn(name = "author_id",nullable = false)
	private Author author;
	
	@Enumerated(EnumType.STRING)
	private Status status;
}
