package pat.android.hochiminhtour;

/**
 * Created by Trúc on 5/14/2019.
 */
// Object Destination
public class Destination {
        String name;
        String location;
        String webpage;
        int image;

    public Destination(String name, String location, String webpage, int image) {
        this.name = name;
        this.location = location;
        this.webpage = webpage;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
