package a9chou.com.fangjiazhuangApp.module.dao;

import java.util.List;

/**
 * date: 2017/10/12.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class SignInBean {

    /**
     * data : {"address":"吧",
     * "content":"啦咯啦",
     * "createDate":"2017-12-01 10:29:29",
     * "deadline":"2017-11-03 00:00:00",
     * "endTime":"2017-12-02 00:00:00",
     * "id":"d839723cd64111e7a6706212970bda1b",
     * "isNewRecord":false,
     * "personList":[{"id":"560598b3750541a8b3b49c47e7e067e4",
     * "isNewRecord":false,
     * "photo":"/treps/userfiles/1/images/cms/article/2017/03/Chrysanthemum.jpg",
     * "signature":"2017-12-01 10:49:02",
     * "telephone":"59738119",
     * "userId":"ec4d7c18cb684a0fbf0968fa4cc7ddd1",
     * "userName":"李润杰"}],"personSum":12,
     * "startTime":"2017-11-30 00:00:00","status":"0","title":"我是编辑活动"}
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
         * address : 吧
         * content : 啦咯啦
         * createDate : 2017-12-01 10:29:29
         * deadline : 2017-11-03 00:00:00
         * endTime : 2017-12-02 00:00:00
         * id : d839723cd64111e7a6706212970bda1b
         * isNewRecord : false
         * personList : [{"id":"560598b3750541a8b3b49c47e7e067e4","isNewRecord":false,"photo":"/treps/userfiles/1/images/cms/article/2017/03/Chrysanthemum.jpg","signature":"2017-12-01 10:49:02","telephone":"59738119","userId":"ec4d7c18cb684a0fbf0968fa4cc7ddd1","userName":"李润杰"}]
         * personSum : 12
         * startTime : 2017-11-30 00:00:00
         * status : 0
         * title : 我是编辑活动
         */

        private String address;
        private String content;
        private String createDate;
        private String deadline;
        private String endTime;
        private String id;
        private boolean isNewRecord;
        private int personSum;
        private String startTime;
        private String status;
        private String title;
        private List<PersonListBean> personList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public int getPersonSum() {
            return personSum;
        }

        public void setPersonSum(int personSum) {
            this.personSum = personSum;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<PersonListBean> getPersonList() {
            return personList;
        }

        public void setPersonList(List<PersonListBean> personList) {
            this.personList = personList;
        }

        public static class PersonListBean {
            /**
             * id : 560598b3750541a8b3b49c47e7e067e4
             * isNewRecord : false
             * photo : /treps/userfiles/1/images/cms/article/2017/03/Chrysanthemum.jpg
             * signature : 2017-12-01 10:49:02
             * telephone : 59738119
             * userId : ec4d7c18cb684a0fbf0968fa4cc7ddd1
             * userName : 李润杰
             */

            private String id;
            private boolean isNewRecord;
            private String photo;
            private String signature;
            private String telephone;
            private String userId;
            private String userName;

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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
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
}
