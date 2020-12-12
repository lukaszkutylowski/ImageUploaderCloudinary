package lukaszkutylowski.api;

import lukaszkutylowski.collection.ImageCollection;
import lukaszkutylowski.frontend.service.FrontendService;
import lukaszkutylowski.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GalleryController {

    private ImageCollection imageCollection;
    private FrontendService frontend;

    @Autowired
    public GalleryController(ImageCollection imageCollection, FrontendService frontend) {
        this.imageCollection = imageCollection;
        this.frontend = frontend;
    }

    @GetMapping("/gallery")
    public String getAll() {
        List<Image> list = imageCollection.findAll();
        return frontend.galleryHtml(list);
   }
}
