package ashiana.com.foodie;

public class Url_Model {

    String url,videoId,image, title;

    public Url_Model(){
        //for firebase
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Url_Model(String url, String videoId, String image, String title) {
        this.url = url;
        this.videoId = videoId;
        this.image = image;
        this.title = title;
    }
}
