package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * Created by zhang on 2018/1/26.
 */

public class OrgId {

    private String name="";
    private String id="";

    public OrgId(String personName, String personId) {
        this.name = personName;
        this.id = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
