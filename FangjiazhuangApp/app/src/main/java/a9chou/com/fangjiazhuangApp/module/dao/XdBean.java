package a9chou.com.fangjiazhuangApp.module.dao;

import java.util.List;

/**
 * date: 2017/12/5.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class XdBean {


    /**
     * data : [{"content":"","contentTitle":"","createBy":"ec4d7c18cb684a0fbf0968fa4cc7ddd1","createDate":"2017-12-02 18:33:37","id":"126c83311041498aa1bcb49044a47ee8","isNewRecord":false,"record":"/userfiles/0/20171202063336.mp3","recordTitle":"","typeId":"0","userId":"1","video":"","videoTitle":""},{"content":"","contentTitle":"","createBy":"ec4d7c18cb684a0fbf0968fa4cc7ddd1","createDate":"2017-12-02 16:58:39","id":"07eaa8074c894bf5aa15541a3b2354bb","isNewRecord":false,"record":"/userfiles/0/20171202045839.mp3","recordTitle":"","typeId":"0","userId":"1","video":"","videoTitle":""},{"content":"","contentTitle":"dsadasd","createBy":"ec4d7c18cb684a0fbf0968fa4cc7ddd1","createDate":"2017-12-02 16:35:21","id":"14dd5c03c2cf40b39a88bf54b08ea30a","isNewRecord":false,"record":"","recordTitle":"","typeId":"1","userId":"1","video":"","videoTitle":""}]
     * type : S
     */

    private String type;
    private List<DataBean> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content :
         * contentTitle :
         * createBy : ec4d7c18cb684a0fbf0968fa4cc7ddd1
         * createDate : 2017-12-02 18:33:37
         * id : 126c83311041498aa1bcb49044a47ee8
         * isNewRecord : false
         * record : /userfiles/0/20171202063336.mp3
         * recordTitle :
         * typeId : 0
         * userId : 1
         * video :
         * videoTitle :
         */

        private String content;
        private String contentTitle;
        private String createBy;
        private String createDate;
        private String id;
        private boolean isNewRecord;
        private String record;
        private String recordTitle;
        private String typeId;
        private String userId;
        private String video;
        private String videoTitle;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentTitle() {
            return contentTitle;
        }

        public void setContentTitle(String contentTitle) {
            this.contentTitle = contentTitle;
        }

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

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public String getRecordTitle() {
            return recordTitle;
        }

        public void setRecordTitle(String recordTitle) {
            this.recordTitle = recordTitle;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }
    }
}
