package freelance.new_syria_v2.categories.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Category",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "type"})
})
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private CategoryType type;
	
	private String nameAr;
	
	private String descriptionAr;

}
