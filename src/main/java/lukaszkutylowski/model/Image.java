package lukaszkutylowski.model;

public class Image {

    private Long id;
    private String imageAddress;

    public Image(){}

    public Image(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
