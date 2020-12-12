package lukaszkutylowski.collection;

import lukaszkutylowski.model.Image;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class ImageCollection {
    private Map<Integer, Image> images = new LinkedHashMap<>();
    private Integer id = 0;

    public Image save(Image image) {
        id += 1;
        images.put(id, image);
        return images.get(id);
    }

    public List<Image> findAll() {
        List<Image> allImages = new LinkedList<>();
        for (int i = 1; i <= images.size(); i++) {
            allImages.add(images.get(i));
        }
        return allImages;
    }
}
