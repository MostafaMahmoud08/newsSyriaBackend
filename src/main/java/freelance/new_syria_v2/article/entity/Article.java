package freelance.new_syria_v2.article.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDate createdAt=LocalDate.now();

	private String header;

	private String bio;
	
	@OneToMany(mappedBy = "article")
	@JsonManagedReference
	List<Section>sections;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "thumbnail_id",referencedColumnName = "id")
	private Image thumbnail;
	
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment>comments;
	
	
}
