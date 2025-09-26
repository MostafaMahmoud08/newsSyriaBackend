package freelance.new_syria_v2.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String fileName;
	
	private String fileType;
	
	@JsonIgnore
	@Lob
	private byte[] imageData;
}
