package pat.android.barcodereader;

/**
 * Created by Trúc on 5/15/2019.
 */
public class Student {
    private String id;
    private String name;
    private String day;
    private int img;

    public Student(String id, String name, String day, int img) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.img = img;
    }

    public Student() {
        this.id = id;
        this.name = name;
        this.day = day;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getImg() {

        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
