package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/9/25.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class LoginBean {

    /**
     * data : {"id":"1","loginName":"admin","mobileLogin":true,"name":"系统管理员","sessionid":"53bb0f46fde1432db08591b26533af94","token":"703df262d3721dbe36d6711c6a8260d6571580e27283b262c9d83e32"}
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
         * id : 1
         * loginName : admin
         * mobileLogin : true
         * name : 系统管理员
         * sessionid : 53bb0f46fde1432db08591b26533af94
         * token : 703df262d3721dbe36d6711c6a8260d6571580e27283b262c9d83e32
         */

        private String id;
        private String loginName;
        private boolean mobileLogin;
        private String name;
        private String sessionid;
        private String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }

        public void setMobileLogin(boolean mobileLogin) {
            this.mobileLogin = mobileLogin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
