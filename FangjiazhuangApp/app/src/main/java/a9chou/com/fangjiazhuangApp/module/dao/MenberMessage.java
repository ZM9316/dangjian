package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * Created by zm9316 on 2017/12/29.
 */

public class MenberMessage {

    private String MenberName;

    public MenberMessage(String MenberName){
        this.MenberName=MenberName;
    }

    public String getMenberName() {
        return MenberName;
    }

    public void setMenberName(String menberName) {
        MenberName = menberName;
    }
}
