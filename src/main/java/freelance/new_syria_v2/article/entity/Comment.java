package freelance.new_syria_v2.article.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import freelance.new_syria_v2.auth.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "TEXT")
	private  String commentContent;
	
	@Enumerated(EnumType.STRING)
	private Status commentStatus;
	
	private LocalDateTime commentDate;
	
	@ManyToOne()
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "article_id",referencedColumnName = "id")
	@JsonIgnore()
	private Article article;
	
}
