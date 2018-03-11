package a9chou.com.fangjiazhuangApp.module.dao;

import java.util.List;

/**
 * date: 2017/11/21.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class PermissionBean {

    /**
     * data : [{"href":"","icon":" icon-briefcase","iconCls":" icon-briefcase","id":"821","menuNo":"","name":"政工组织","objId":"","permission":"","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"815","menuNo":"","name":"编辑","objId":"","permission":"dm:dmDoc:edit","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"814","menuNo":"","name":"查看","objId":"","permission":"dm:dmDoc:view","remarks":"","target":"","type":""},{"id":"d4f44f6d845c469f9dc0680fa4a73388","name":"查看","permission":"wp:wpPartyCost:view"},{"id":"90bbcb55c0f54703befe6e3649ec89b7","name":"查看","permission":"wp:wpPetition:view"},{"id":"c617c56b6fd94378b340e1615cf58763","name":"查看","permission":"sys:sysAppUpdate:view"},{"href":"/wp/wpPartyCost/form","id":"3c9dfa76b4fc4ceea0356a9f9c405d42","name":"党费管理"},{"href":"","icon":"","iconCls":"","id":"ee210346cb124c9c918dde11ab7db2d1","menuNo":"","name":"新增","objId":"","permission":"wp:wpTogether:add","remarks":"","target":"","type":""},{"id":"63e0fe49ee7243a590246cd5396968cd","name":"查看","permission":"tm:tmRule:view"},{"id":"f29cbd35a21a4166873c8a5a810407d0","name":"查看","permission":"tm:tmGradeIndexType:view"},{"id":"fe4ab2e0e8c94510bc79a3a467ad8247","name":"查看","permission":"sd:sdSoReqPlan:view"},{"id":"9eb7f9d7597a4823ae72004092148bf9","name":"查看","permission":"wp:wpIssue:view"},{"href":"","icon":"","iconCls":"","id":"834","menuNo":"","name":"新增","objId":"","permission":"wp:wpPetition:add","remarks":"","target":"","type":""},{"id":"b8bd537c42d74b89980a3d9be0c00216","name":"查看","permission":"sys:sysDuties:view"},{"href":"","icon":"","iconCls":"","id":"835","menuNo":"","name":"删除","objId":"","permission":"wp:wpPetition:del","remarks":"","target":"","type":""},{"id":"6716d84d6ec845da800de24c525f509e","name":"查看","permission":"sd:sdCustYearManage:view"},{"id":"4897103faec84a72a9cc5334d032ec9c","name":"查看","permission":"wp:wpChat:view"},{"id":"0c09b0f3d1d74706a492b429efdb6c59","name":"查看","permission":"wp:wpIssue:view"},{"id":"2e97273db9614dc0b7c555fafa1a391e","name":"查看","permission":"sys:sysOtherOrg:view"},{"id":"812567041b524a11ac0410470c495d66","name":"查看","permission":"wp:wpFlow:view"},{"id":"3137c1f17f2042aea8875dcecc10171c","name":"查看","permission":"wo:woSot:view"},{"id":"2bcf9ebfbbe94eb7a277d73cf91cb8b7","name":"查看","permission":"wp:wpExperience:view"},{"id":"7a1c2fa6f3ff4cf4beef6668ae76e33a","name":"查看","permission":"tm:tmSafeAimClass:view"},{"id":"2d478cf6bca14e40999a2e9df206ee98","name":"查看","permission":"sd:sdSalesCatalog:view"},{"id":"3e5105c844594f74bf63c91c2cbf9d5b","name":"查看","permission":"wp:wpTogether:view"},{"id":"c912d8f1219048edba1d8d18feede22c","name":"查看","permission":"pg:pgSpec:view"},{"id":"d74103fdef6a4435837168b086e3c282","name":"查看","permission":"wp:wpMeeting:view"},{"id":"d804dbef72924d37a13007ccb3969733","name":"查看","permission":"wp:wpIssue:view"},{"id":"4095bd1226954f8e8b18ad99d0f889f5","name":"查看","permission":"wp:wpMessage:view"},{"id":"cf2f7e9d9a1f414bb51568596084fb09","name":"查看","permission":"tm:tmToolClass:view"},{"id":"d365226a955d4f47988116661a37e4dd","name":"编辑","permission":"wp:wpExperience:edit"},{"href":"","icon":"","iconCls":"","id":"822","menuNo":"","name":"新增","objId":"","permission":"wp:wpIssue:add","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"823","menuNo":"","name":"删除","objId":"","permission":"wp:wpIssue:del","remarks":"","target":"","type":""},{"id":"b9156c4507174b6a833e438e74a0fe9d","name":"编辑","permission":"sys:sysAppUpdate:edit"},{"id":"1c977c5de13d44d5b5ca180f9a913162","name":"编辑","permission":"sys:sysDuties:edit"},{"id":"5f56743281c4470083637ae1be9b4aff","name":"编辑","permission":"wp:wpPartyCost:edit"},{"id":"9b8ba6dab2a54a83b45c9da2064ec146","name":"编辑","permission":"wp:wpChat:edit"},{"id":"98921b1c84f440db8c14771950ca0cc6","name":"编辑","permission":"wp:wpTogether:edit"},{"id":"a65fa09f467d45408aa49873576d6450","name":"编辑","permission":"wp:wpMeeting:edit"},{"id":"a6afb8628b5f4a36ab836133d190f6d0","name":"编辑","permission":"wp:wpPetition:edit"},{"id":"b528aa3b693c48bdb92e62f89c72bcc2","name":"编辑","permission":"wp:wpMessage:edit"},{"id":"4ae05386847043539816d038de840862","name":"编辑","permission":"sys:sysOtherOrg:edit"},{"href":"","icon":"","iconCls":"","id":"824","menuNo":"","name":"创建","objId":"","permission":"wp:wpMeeting:add","remarks":"","target":"","type":""},{"id":"dc2abdb3e6ed42d78bc69c72b6d1bde6","name":"编辑","permission":"wp:wpFlow:edit"},{"href":"","icon":"","iconCls":"","id":"825","menuNo":"","name":"删除","objId":"","permission":"wp:wpMeeting:del","remarks":"","target":"","type":""},{"id":"786d3b31798a48c6b105fdb3669f762d","name":"编辑","permission":"wp:wpIssue:edit"},{"href":"","icon":"","iconCls":"","id":"828","menuNo":"","name":"删除","objId":"","permission":"wp:wpTogether:del","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"829","menuNo":"","name":"查看","objId":"","permission":"wp:wpTogether:view","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"827","menuNo":"","name":"参会人员新增","objId":"","permission":"wp:wpMeetingPerson:add","remarks":"","target":"","type":""},{"id":"427a935e6b8d48fdb739e9f50564e94b","name":"参会人员查看","permission":"wp:wpMeetingPerson:view"},{"id":"471dabeffeba42fa9345d2688f241e09","name":"评论表查看","permission":"wp:wpTogetherComment:view"},{"href":"","icon":"","iconCls":"","id":"830","menuNo":"","name":"评论表新增","objId":"","permission":"wp:wpTogetherComment:add","remarks":"","target":"","type":""},{"id":"1625706b9251434895fe36a3ef4db8b4","name":"其他组织机构与职位对应表查看","permission":"sys:sysOrgDuties:view"},{"id":"aedb61b3800746e8988b595eb7625496","name":"消息人员查看","permission":"wp:wpMessagePerson:view"},{"href":"","icon":"","iconCls":"","id":"826","menuNo":"","name":"参会人员删除","objId":"","permission":"wp:wpMeetingPerson:del","remarks":"","target":"","type":""},{"id":"5a17a0c4bf9b464580ad5d2dd05afec1","name":"其他组织机构与职位对应表编辑","permission":"sys:sysOrgDuties:edit"},{"id":"4e6c911d9fb34b6da19df4168e52df68","name":"消息人员编辑","permission":"wp:wpMessagePerson:edit"},{"id":"926aa580fa4d46bbaaa4f3994069706d","name":"参会人员编辑","permission":"wp:wpMeetingPerson:edit"},{"id":"ab3d5f9682d94191b402233e9d4afeac","name":"评论表编辑","permission":"wp:wpTogetherComment:edit"},{"href":"","icon":"","iconCls":"","id":"831","menuNo":"","name":"评论表删除","objId":"","permission":"wp:wpTogetherComment:del","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"841","menuNo":"","name":"参会人员签到","objId":"","permission":"wp:wpMeetingPerson:signature","remarks":"","target":"","type":""},{"href":"","icon":" icon-fire","iconCls":" icon-fire","id":"838","menuNo":"","name":"党员流动","objId":"","permission":"","remarks":"","target":"","type":""},{"id":"5cfff9e5d1e14812ae541f7704e3a6fe","name":"活动参与人查看","permission":"wp:wpTogetherPerson:view"},{"href":"","icon":"","iconCls":"","id":"832","menuNo":"","name":"活动参与人新增","objId":"","permission":"wp:wpTogetherPerson:add","remarks":"","target":"","type":""},{"href":"","icon":"","iconCls":"","id":"833","menuNo":"","name":"活动参与人删除","objId":"","permission":"wp:wpTogetherPerson:del","remarks":"","target":"","type":""},{"id":"4e5c6b60dd414df3ae876e4c03072896","name":"活动参与人编辑","permission":"wp:wpTogetherPerson:edit"},{"href":"/sys/sysOtherOrg/form?treeQuery=61&baseQuery=61","icon":" icon-plus-sign","iconCls":" icon-plus-sign","id":"842","menuNo":"","name":"工会结构","objId":"","permission":"","remarks":"","target":"","type":""}]
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
         * href :
         * icon :  icon-briefcase
         * iconCls :  icon-briefcase
         * id : 821
         * menuNo :
         * name : 政工组织
         * objId :
         * permission :
         * remarks :
         * target :
         * type :
         */

        private String href;
        private String icon;
        private String iconCls;
        private String id;
        private String menuNo;
        private String name;
        private String objId;
        private String permission;
        private String remarks;
        private String target;
        private String type;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIconCls() {
            return iconCls;
        }

        public void setIconCls(String iconCls) {
            this.iconCls = iconCls;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMenuNo() {
            return menuNo;
        }

        public void setMenuNo(String menuNo) {
            this.menuNo = menuNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObjId() {
            return objId;
        }

        public void setObjId(String objId) {
            this.objId = objId;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
