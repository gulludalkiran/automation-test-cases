package TrelloTest.models;

public class BoardModel extends BaseModel {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + url;
    }
}
