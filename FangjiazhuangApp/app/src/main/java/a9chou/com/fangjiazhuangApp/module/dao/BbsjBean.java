package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/11/20.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class BbsjBean {

    private String title;
    private String time;
    private String content;
    private String btnvalue="";
    private String attendName;
    private String attendId="";
    private String personName;
    private String personId="";
    private String IdsJ;
    private String IdsA;
    private String biaoshi;
    private String place;
    private String place_left;
    private Boolean checked=false;

    public BbsjBean() {

    }
    public BbsjBean(String place,String place_left,String x) {
        this.place=place;
        this.place_left=place_left;
    }
    public BbsjBean(Boolean checked) {
        this.checked = checked;
    }
    public BbsjBean(String title, String time, String content, String btnvalue,String biaoshi) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.btnvalue = btnvalue;
        this.biaoshi = biaoshi;
    }
    public BbsjBean(String title, String time, String content, String btnvalue) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.btnvalue = btnvalue;
    }
    public BbsjBean(String personName, String personId) {
        this.personName = personName;
        this.personId = personId;
    }
    public BbsjBean(String personName, String personId,String x,String y,String z,String a) {
        this.attendName = personName;
        this.attendId = personId;
    }

    public BbsjBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getBtnvalue() {
        return btnvalue;
    }

    public void setBtnvalue(String btnvalue) {
        this.btnvalue = btnvalue;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getIdsJ() {
        return IdsJ;
    }

    public void setIdsJ(String idsJ) {
        IdsJ = idsJ;
    }

    public String getIdsA() {
        return IdsA;
    }

    public void setIdsA(String idsA) {
        IdsA = idsA;
    }

    public String getBiaoshi() {
        return biaoshi;
    }

    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace_left() {
        return place_left;
    }

    public void setPlace_left(String place_left) {
        this.place_left = place_left;
    }

    public String getAttendName() {
        return attendName;
    }

    public void setAttendName(String attendName) {
        this.attendName = attendName;
    }

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }
}
