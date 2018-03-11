package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/9/12.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class Bean {


    private String name;
    private String content;
    private String time;
    private int image;

    public Bean(String name, String content, String time, int image) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.image = image;
    }

    public Bean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
