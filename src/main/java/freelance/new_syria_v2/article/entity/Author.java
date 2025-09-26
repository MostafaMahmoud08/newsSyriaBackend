package freelance.new_syria_v2.article.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String userName;
	
	@Email()
	private String email;
	
	private String phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cover_id",referencedColumnName = "id")
	private Image cover;
	
	@OneToMany(mappedBy = "author")
	private List<Article>articles;

	private String countryName;
}
