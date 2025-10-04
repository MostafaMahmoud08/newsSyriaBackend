package freelance.new_syria_v2.article.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImageUtil {

	private final ImageRepository repository;
	@Value("${spring.app.server-name}")
	private String serverName;

	public Image from(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty or missing");
		}
		Image image = new Image();
		image.setFileName(file.getOriginalFilename());
		image.setFileType(file.getContentType());
		try {
			image.setImageData(file.getBytes());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to process file", e);
		}
		return repository.save(image);
	}

	public String imageUrl(UUID uuid) {
		return serverName + "/images/" + uuid;
	}
}
