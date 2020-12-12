package lukaszkutylowski.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lukaszkutylowski.collection.ImageCollection;
import lukaszkutylowski.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageCollection imageCollection;

    @Autowired
    public ImageUploader(ImageCollection imageCollection) {
        this.imageCollection = imageCollection;

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dj2vinf28",
                "api_key", "118613721751983",
                "api_secret", "CXxvtP9CPZuNAZunJaBnMSvQyuU"
        ));
    }

    public String get(String path) {
        File file = new File(path);
        Map uploadResult = null;

        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageCollection.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResult.get("url").toString();
    }

}
