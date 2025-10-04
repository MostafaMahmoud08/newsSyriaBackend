package freelance.new_syria_v2.article.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String reactionType;
}
