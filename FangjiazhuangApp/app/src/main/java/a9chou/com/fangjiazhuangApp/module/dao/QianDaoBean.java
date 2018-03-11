package a9chou.com.fangjiazhuangApp.module.dao;

/**
 * date: 2017/11/29.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class QianDaoBean {
    /**
     * data : {"isNewRecord":true,
     * "meetingId":"2c1d8f69b47f11e7ad516212970bda1b",
     * "signature":"2017-11-29 12:00:15",
     * "userId":"0197fcf4f9d74fbbb0c802fb4fca543d",
     * "userName":"admin"}
     * message : 签到成功
     * type : s
     */

    private DataBean data;
    private String message;
    private String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean {
        /**
         * isNewRecord : true
         * meetingId : 2c1d8f69b47f11e7ad516212970bda1b
         * signature : 2017-11-29 12:00:15
         * userId : 0197fcf4f9d74fbbb0c802fb4fca543d
         * userName : admin
         */

        private boolean isNewRecord;
        private String meetingId;
        private String signature;
        private String userId;
        private String userName;

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
