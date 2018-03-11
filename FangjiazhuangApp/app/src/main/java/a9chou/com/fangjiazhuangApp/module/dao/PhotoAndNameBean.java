package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/12/4.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class PhotoAndNameBean {

    /**
     * data : {"admin":false,"age":0,"isNewRecord":true,"loginFlag":"1","name":"李润杰","photo":"/treps/userfiles/1/images/cms/article/2017/03/Chrysanthemum.jpg","roleNames":"","siteId":"100"}
     * type : S
     */

    private DataBean data;
    private String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean {
        /**
         * admin : false
         * age : 0
         * isNewRecord : true
         * loginFlag : 1
         * name : 李润杰
         * photo : /treps/userfiles/1/images/cms/article/2017/03/Chrysanthemum.jpg
         * roleNames :
         * siteId : 100
         */

        private boolean admin;
        private int age;
        private boolean isNewRecord;
        private String loginFlag;
        private String name;
        private String photo;
        private String roleNames;
        private String siteId;

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }
    }
}
