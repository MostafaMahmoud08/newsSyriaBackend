package freelance.new_syria_v2.article.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String header;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cover_image_id",referencedColumnName = "id")
	private Image coverImage;
	
	private String imageUrl;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@ManyToOne()
	@JsonIgnore()
	@JoinColumn(name = "article_id",nullable = false)
	private Article article;
}
