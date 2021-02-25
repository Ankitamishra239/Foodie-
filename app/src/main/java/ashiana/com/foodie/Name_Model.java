package ashiana.com.foodie;

public class Name_Model {
    
    String name;
    String url;

    public Name_Model(){
        //for firebase
    }

    public Name_Model(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
