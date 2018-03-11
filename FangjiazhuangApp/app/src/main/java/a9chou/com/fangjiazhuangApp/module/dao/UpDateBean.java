package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/12/2.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class UpDateBean {

    /**
     * data : {"createBy":"1","createDate":"2017-12-02 15:28:27","id":"1","isNewRecord":false,"remarks":"1.更新内容","siteId":"100","updateBy":"1","updateDate":"2017-12-02 15:28:30","url":"/userfiles/null/1.1.apk","version":2,"versionName":"1.1"}
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
         * createBy : 1
         * createDate : 2017-12-02 15:28:27
         * id : 1
         * isNewRecord : false
         * remarks : 1.更新内容
         * siteId : 100
         * updateBy : 1
         * updateDate : 2017-12-02 15:28:30
         * url : /userfiles/null/1.1.apk
         * version : 2
         * versionName : 1.1
         */

        private String createBy;
        private String createDate;
        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String siteId;
        private String updateBy;
        private String updateDate;
        private String url;
        private int version;
        private String versionName;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
