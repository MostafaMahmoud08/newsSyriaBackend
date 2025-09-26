package freelance.new_syria_v2.article.controller;

import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.repository.ImageRepository;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
    	    Image image = this.imageRepository.findById(id)
    	            .orElseThrow(() -> new NotFoundException("Image not found"));

    	    return ResponseEntity.status(HttpStatus.OK)
    	            .contentType(MediaType.valueOf(image.getFileType()))
    	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getFileName() + "\"")
    	            .body(image.getImageData());
    	}
}

