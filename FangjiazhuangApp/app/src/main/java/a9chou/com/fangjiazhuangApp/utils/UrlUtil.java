package a9chou.com.fangjiazhuangApp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static a9chou.com.fangjiazhuangApp.utils.Config.IP;

/**
 * date: 2017/9/27.
 * author: 王艺凯 (lenovo )
 * function:    存放 Html5的Url
 */

public class UrlUtil {

    public static SharedPreferences getShared(Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp;
    }


    public static String getWithNo(Activity a) {
        String username = getShared(a).getString("username", "");
        String token = getShared(a).getString("token", "");
        String userId = getShared(a).getString("userId", "");
        return "?userName=" + username + "&token=" + token + "&userId=" + userId;
    }

    public static String getWithHas(Activity a) {

        String username = getShared(a).getString("username", "");
        String token = getShared(a).getString("token", "");
        String userId = getShared(a).getString("userId", "");
        return "&userName=" + username + "&token=" + token + "&userId=" + userId;
    }

    //    服务器 htmlurl前缀
//    public static final String FATHER_HTML = "http://111.205.44.47/treps/static/mobile/wp/";
//    public static final String FATHER_HTML = IP+"treps/static/mobile/wp/";
    public static final String FATHER_HTML = IP+"/treps/static/mobile/wp/";
//    public static final String FATHER_HTML = "http://10.24.58.82:8181/treps/static/mobile/wp/";
    //    测试
//    public static final String FATHER_HTML = "http://10.24.64.104:8181/treps/static/mobile/wp/";
    //    大事件
    public static final String BIG_EVENTS = "SDeedsList.html?";
    //    思想建设
    public static final String IDEOLOGICAL_BUILDING = "SIdeolCon.html";
    //    党员服务
    public static final String PARTY_SERVICE = "SPartySer.html";
    //    制度建设
    public static final String ZDJS = "SSystemCon.html";
    //    党建动态
    public static final String DJDT = "SDeedsDetial.html";
    public static final String HONOURS = "SHonorShow.html";
    //    在线学习
    public static final String LEARNONLINE = "nOnlineLear.html";
    //    三会一课
    public static final String SHYK = "nMeetingClas.html";
    //    三会一课新增
    public static final String SHYKXZ = "nMeetingClasCreat.html";
    //    书记在线
    public static final String SJZX = "nSecretOnline.html";
    //    信件填写
    public static final String XJTX = "nSecretOnlineWrite.html";
    //    谈话谈心
    public static final String THTX = "nTalking.html";
    //    活动管理
    public static final String HDGL = "nPartyActivities.html";
    //    添加活动
    public static final String TJHD = "nPartyActiveCreat.html?isNewRecord=true";
    //     活动留言
    public static final String HDLY = "nPartyActiveMessage.html";
    //     活动详情
    public static final String HDXQ = "nPartyActivitiesDet.html";
    //     党员信息
    public static final String DYXXCK = "nYouthInforma.html?flag=1";
    //     团员信息
    public static final String TYXX = "nYouthInforma.html?flag=0";
    //     积极分子信息
    public static final String JJFZXX = "nYouthInforma.html?flag=2";
    //     青年信息
    public static final String QNXX = "nYouthInforma.html?flag=3";


    public static final String ZZJJT = "coded.html?typeId=0";
    public static final String GHGL = "coded.html?typeId=1";
    public static final String JJZZ = "coded.html?typeId=2";
    public static final String TQZZ = "coded.html?typeId=3";

    //     党费管理
    public static final String DFGL = "MduesMoney.html";


    public static final String LDBH = "nEfficiSuperv.html?categoryId=57";
    public static final String WTHD = "nEfficiSuperv.html?categoryId=58";
    public static final String VGJS = "nEfficiSuperv.html?categoryId=59";
    public static final String MZGL = "nEfficiSuperv.html?categoryId=60";
    //     团青工作管理
    public static final String TQGZGL = "nEfficiSuperv.html?categoryId=45";


    public static final String JJJC = "nEfficiSuperv.html?categoryId=61";
    public static final String XNJC = "nEfficiSuperv.html?categoryId=62";
    public static final String JDZR = "nEfficiSuperv.html?categoryId=63";
    public static final String SJGL = "nEfficiSuperv.html?categoryId=64";
    public static final String XFGL = "nLettersManagement.html";
    public static final String DYLDXX = "nOrganInforma.html?flow=1";
    //     转入转出
    public static final String ZRZC = "nOrganInforma.html?flow=0";
    public static final String QYWH = "nCulture.html";
    //     我的活动

    public static final String MYACTIVE = "myActive.html";


    //     我的会议
    public static final String MYMEETING = "myMetting.html";
    //     我的消息
    public static final String MYXX = "myNews.html";
    public static final String WDXX = "myNewsWei.html";
    public static final String HDBJ = "nPartyActiveCreat.html";
    //      个人信息
    public static final String GRXX = "myDetails.html";
    //      我阅读的书籍
    public static final String READ = "myBooks.html";
    public static final String SETPASSWORD = "myPassword.html";
    public static final String SETMESSAGE = "myDetailsEdit.html";
    //      团青活动、
    public static final String TQHD = "nEfficiSuperv.html?categoryId=82";
    //      团员统计
    public static final String MEMBER_STATISTICS = "nYouthStatistics.html";
    //      服务型党组织   83
    public static final String SERVICE_ORAGIZATION = "Stporganization.html";
    //      党章党规   85
    public static final String PARTY_CONSTITUTION = "Pconstitution.html";
    //      总书记讲话   86
    public static final String GENERAL_SECRETARY= "Gsecretary.html";
    //      普法教育  87
    public static final String UNIVERSAL_EDUCATION = "Ueducation.html";
    //      考核内容  90
    public static final String EXAMINATION_CNTENT = "Econtent.html";
    //      党建考核  91
    public static final String BUILD_ASSESSMENT= "Tassessmentpb.html";
    //      党廉考核  92
    public static final String PARTY_ASSESSMENT= "Texaminationpl.html";
    //      工会考核  93
    public static final String LABOUR_UNION_ASSESSMENT = "Tassessment.html";
    //      团青考核  94
    public static final String YOUTH_ASSESSMENT= "Yexamination.html";
    //      考核通报  95
    public static final String ASSESSMENT_BULLETIN= "Abulletin.html";
    //      规定动作清单
    public static final String MOVEMENT= "movement.html";
    //      支部规制
    public static final String REGULATION= "regulation.html";
    //      年度计划
    public static final String PLAN= "plan.html";
//    //      党员信息
//    public static final String INFOMATION= "information.html";
    //      道德讲堂
    public static final String MORALITY= "morality.html";
    //      支部品牌
    public static final String BRAND= "brand.html";
    //      党务公开
    public static final String AFFAIRS= "affairs.html";
    //      党委书记
//    public static final String SPACTION= "Secretary_prescribed_action.html?";
    public static final String SPACTION= "SubjectResponsibility.html?";
    //      总经理
//    public static final String GMPACTION= "general_manager_prescribed_action.html?";
    public static final String GMPACTION= "SubjectResponsibility.html?";
    //      党委副书记
//    public static final String DSPACTION= "Deputy_Secretary_prescribed_action.html?";
    public static final String DSPACTION= "SubjectResponsibility.html?";
    //      纪委书记
//    public static final String CDSPACTION= "Commission_Discipline_Secretary_prescribed_action.html?";
    public static final String CDSPACTION= "SubjectResponsibility.html?";
    //      党委领导班子
//    public static final String LRACTION= "leadership_ranks.html?";
    public static final String LRACTION= "SubjectResponsibility.html?";
    //      政治工作部
    public static final String DPWACTION= "Department_political_work.html?";
    //      监察审计部(纪检办)
//    public static final String DIOACTION= "Discipline_Inspection_Office.html?";
    public static final String DIOACTION= "SubjectResponsibility.html?";
    //      综合党支部
//    public static final String CPBACTION= "Comprehensive_Party_branch.html?";
    public static final String CPBACTION= "SubjectResponsibility.html?";
    //      经营党支部
    public static final String RPBACTION= "Run_Party_branch.html?";
    //      工程党支部
    public static final String EPTBACTION= "Engineering_Party_branch.html?";
    //      运行党支部
    public static final String FPBACTION= "Function_Party_branch.html?";
    //      设备党支部
    public static final String EPPBACTION= "Equipment_Party_branch.html?";
    //      党务公开
    public static final String PGOPA= "party_government_open_party_affairs.html?";
    //      厂务公开
    public static final String PFA= "publicity_factory_affairs.html?";
    //      党建政工组织机构206
    public static final String PGO= "nDisciplineiNspecDet.html?&categoryId=206&Id=779";
    //      党建政工职能部室
    public static final String PGFD= "nDisciplineiNspecDet.html?&categoryId=207&Id=780";
    //      党建政工基层组织
    public static final String PGGO= "nDisciplineiNspecDet.html?&categoryId=208&Id=756";
    //      学习计划
    public static final String PGLP= "party_government_learning_plan.html?";
    //      视频教育
    public static final String PGVE= "party_government_video_education.html?";
    //      书香宁电
    public static final String PGE= "party_government_electricity.html?";
    //      纪检监审组织机构
    public static final String DIO= "nDisciplineiNspecDet.html?&categoryId=210&Id=1004";
    //      纪检监审职能部室
    public static final String DIFD= "nDisciplineiNspecDet.html?&categoryId=211&Id=1005";
    //      纪检监审基层纪检
    public static final String DIGO= "nDisciplineiNspecDet.html?&categoryId=212&Id=1006";
    //      党纪检查
    public static final String DIP= "Discipline_inspection_party.html?";
    //      监督责任
    public static final String DISR= "Discipline_inspection_supervisory_responsibility.html?";
    //      立项项目
    public static final String DIPP= "Discipline_inspection_Project_project.html?";
    //      监察建议
    public static final String DISRS= "Discipline_inspection_Supervisory_recommendations.html?";
    //      整改完成
    public static final String DIRD= "Discipline_inspection_Rectification_done.html?";
    //      工会管理组织机构
    public static final String GMO= "nDisciplineiNspecDet.html?&categoryId=223&Id=762";
    //      工会管理基层组织
    public static final String GAGRO= "nDisciplineiNspecDet.html?&categoryId=224&Id=763";
    //      职代会
    public static final String GMTWC= "Guild_management_The_workers_Congress.html?";
    //      权益保障
    public static final String GMPORI= "Guild_management_Protection_of_rights_interests.html?";
    //      创新创效
    public static final String GMIC= "Guild_management_Innovation_creation.html?";
    //      竞技竞赛
    public static final String GMCC= "Guild_management_Competitive_competition.html?";
    //      女工委
    public static final String GMWW= "nDisciplineiNspecDet.html?&categoryId=231&Id=899";//231-899
    //      巾帼建功
    public static final String GMM= "Guild_management_Meritorious.html?";
    //      团委委员
    public static final String MOLCMOTLC= "nDisciplineiNspecDet.html?&categoryId=229&Id=772";//229-772
    //      基层组织
    public static final String MOLGRO= "nDisciplineiNspecDet.html?&categoryId=230&Id=764";
    //      一学一做
    public static final String MOLOSAOD= "Management_ofYouth_League_One_study_and_one_do.html?";
    //      五小QC
    public static final String MOLF= "Management_ofYouth_League_FiveQC.html?";
    //      号手岗队
    public static final String MOLTGT= "Management_ofYouth_League_The_gang_team.html?";
    //      道德讲堂
    public static final String CCMLH= "Cultural_civilization_Moral_lecture_hall.html?";
    //      文化宣贯
    public static final String CCC= "Cultural_civilization_Culture.html?";
    //      民族团结
    public static final String CCNU= "Cultural_civilization_National_unity.html?";
    //      共建共荣
    public static final String CCCP= "Cultural_civilization_construction_prosperity.html?";
    //      综合治理
    public static final String CCCC= "Cultural_civilization_comprehensive_control.html?";
    //      综合党支部组织机构
    public static final String PBCO= "nDisciplineiNspecDet.html?&categoryId=251&Id=781";
    //      综合党支部责任清单
    public static final String PBCLR= "Party_Building_comprehensive_List_responsibilities.html?";
    //      综合党支部党员发展
    public static final String PBCTDPM= "Party_Building_comprehensive_The_development_Party_members.html?";
    //      综合党支部党务公开
    public static final String PBCOPA= "Party_Building_comprehensive_Open_party_affairs.html?";
    //      综合党支部支部品牌
    public static final String PBCBB= "Party_Building_comprehensive_Branch_brand.html?";
    //      经营党支部组织机构
    public static final String PBMO= "nDisciplineiNspecDet.html?&categoryId=256&Id=782";
    //      经营党支部责任清单
    public static final String PBMLR= "Party_Building_Management_List_responsibilities.html?";
    //      经营党支部党员发展
    public static final String PBMTDPM= "RunPartyBranch.html?";
    //      经营党支部党务公开
    public static final String PBMOPA= "Party_Building_Management_Open_party_affairs.html?";
    //      经营党支部支部品牌
    public static final String PBMBB= "Party_Building_Management_Branch_brand.html?";
    //      运行党支部组织机构
    public static final String PBFO= "nDisciplineiNspecDet.html?&categoryId=261&Id=783";
    //      运行党支部责任清单
    public static final String PBFLR= "Party_Building_Function_List_responsibilities.html?";
    //      运行党支部党员发展
    public static final String PBFTDPM= "Party_Building_Function_The_development_Party_members.html?";
    //      运行党支部党务公开
    public static final String PBFOPA= "Party_Building_Function_Open_party_affairs.html?";
    //      运行党支部支部品牌
    public static final String PBFBB= "Party_Building_Function_Branch_brand.html?";
    //      设备党支部组织机构
    public static final String PBEO= "nDisciplineiNspecDet.html?&categoryId=266&Id=787";
    //      设备党支部责任清单
    public static final String PBELR= "Party_Building_equipment_List_responsibilities.html?";
    //      设备党支部党员发展
    public static final String PBETDPM= "Party_Building_equipment_The_development_Party_members.html?";
    //      设备党支部党务公开
    public static final String PBEOPA= "Party_Building_equipment_Open_party_affairs.html?";
    //      设备党支部支部品牌
    public static final String PBEBB= "Party_Building_equipment_Branch_brand.html?";
    //      工程党支部组织机构
    public static final String PBEOG= "nDisciplineiNspecDet.html?&categoryId=271&Id=785";
    //      工程党支部责任清单
    public static final String PBELRG= "Party_Building_engineering_List_responsibilities.html?";
    //      工程党支部党员发展
    public static final String PBETDPMG= "Party_Building_engineering_The_development_Party_members.html?";
    //      工程党支部党务公开
    public static final String PBEOPAG= "Party_Building_engineering_Open_party_affairs.html?";
    //      工程党支部支部品牌
    public static final String PBEBBG= "Party_Building_engineering_Branch_brand.html?";
    //      综合分工会组织机构
    public static final String DLCO= "nDisciplineiNspecDet.html?&categoryId=282&Id=773";//282-773
    //      综合分工会责任清单
    public static final String DLCLR= "Division_labor_comprehensive_List_responsibilities.html?";
    //      综合分工会党员发展
    public static final String DLCTDPM= "Division_labor_comprehensive_The_development_Party_members.html?";
    //      综合分工会党务公开
    public static final String DLCOPAS= "Division_labor_comprehensive_Open_party_affairs.html?";
    //      综合分工会支部品牌
    public static final String DLCBB= "Division_labor_comprehensive_Branch_brand.html?";
    //      经营分工会组织机构
    public static final String DLMO= "nDisciplineiNspecDet.html?&categoryId=288&Id=774";//288-774
    //      经营分工会责任清单
    public static final String DLMLR= "Division_labor_Management_List_responsibilities.html?";
    //      经营分工会党员发展
    public static final String DLMTDPM= "Division_labor_Management_The_development_Party_members.html?";
    //      经营分工会党务公开
    public static final String DLMOPA= "Division_labor_Management_Open_party_affairs.html?";
    //      经营分工会支部品牌
    public static final String DLMBB= "Division_labor_Management_Branch_brand.html?";
    //      运行分工会组织机构
    public static final String DLFO= "nDisciplineiNspecDet.html?&categoryId=293&Id=777";//293-777
    //      运行分工会责任清单
    public static final String DLFLR= "Division_labor_Function_List_responsibilities.html?";
    //      运行分工会党员发展
    public static final String DLFTDPM= "Division_labor_Function_The_development_Party_members.html?";
    //      运行分工会党务公开
    public static final String DLFOPA= "Division_labor_Function_Open_party_affairs.html?";
    //      运行分工会支部品牌
    public static final String DLFBB= "Division_labor_Function_Branch_brand.html?";
    //      设备分工会组织机构
    public static final String DLEO= "nDisciplineiNspecDet.html?&categoryId=298&Id=775";//298-775
    //      设备分工会责任清单
    public static final String DLELR= "Division_labor_equipment_List_responsibilities.html?";
    //      设备分工会党员发展
    public static final String DLETDPM= "Division_labor_equipment_The_development_Party_members.html?";
    //      设备分工会党务公开
    public static final String DLEOPA= "Division_labor_equipment_Open_party_affairs.html?";
    //      设备分工会支部品牌
    public static final String DLEBB= "Division_labor_equipment_Branch_brand.html?";
    //      工程分工会组织机构
    public static final String DLEOG= "nDisciplineiNspecDet.html?&categoryId=303&Id=776";//303-776
    //      工程分工会责任清单
    public static final String DLELRG= "Division_labor_engineering_List_responsibilities.html?";
    //      工程分工会党员发展
    public static final String DLETDPMG= "Division_labor_engineering_The_development_Party_members.html?";
    //      工程分工会党务公开
    public static final String DLEOPAG= "Division_labor_engineering_Open_party_affairs.html?";
    //      工程分工会支部品牌
    public static final String DLEBBG= "Division_labor_engineering_Branch_brand.html?";
    //      综合团支部组织机构
    public static final String IMBO= "nDisciplineiNspecDet.html?&categoryId=346&Id=870";//346-870
    //      综合团支部责任清单
    public static final String IMBLR= "IntegratedMissionBranchListResponsibilities.html?";
    //      综合团支部党员发展
    public static final String IMBDL= "IntegratedMissionBranchDevelopmentLeagueMembers.html?";
    //      综合团支部党务公开
    public static final String IMBP= "IntegratedMissionBranchPublicAffairs.html?";
    //      经营团支部组织机构
    public static final String BGBO= "nDisciplineiNspecDet.html?&categoryId=350&Id=871";//350-871
    //      经营团支部责任清单
    public static final String BGBLR= "BusinessGroupBranchListResponsibilities.html?";
    //      经营团支部党员发展
    public static final String BGBDL= "BusinessGroupBranchDevelopmentLeagueMembers.html?";
    //      经营团支部党务公开
    public static final String BGBP= "BusinessGroupBranchPublicAffairs.html?";
    //      运行团支部组织机构
    public static final String OGBLO= "nDisciplineiNspecDet.html?&categoryId=362&Id=874";//362-874
    //      运行团支部责任清单
    public static final String OGBLR= "OperationGroupBranchListResponsibilities.html?";
    //      运行团支部党员发展
    public static final String OGBDL= "OperationGroupBranchDevelopmentLeagueMembers.html?";
    //      运行团支部党务公开
    public static final String OGBPA= "OperationGroupBranchPublicAffairs.html?";
    //      设备团支部组织机构
    public static final String UBBO= "nDisciplineiNspecDet.html?&categoryId=358&Id=873";//358-873
    //      设备团支部责任清单
    public static final String UBBLR= "UnitBranchBranchListResponsibilities.html?";
    //      设备团支部团员发展
    public static final String UBBDL= "UnitBranchBranchDevelopmentLeagueMembers.html?";
    //      设备团支部团务公开
    public static final String UBBPA= "UnitBranchBranchPublicAffairs.html?";
    //      工程团支部组织机构
    public static final String ERBO= "nDisciplineiNspecDet.html?&categoryId=354&Id=872";//354-872
    //      工程团支部责任清单
    public static final String ERB= "EngineeringRegimentBranch.html?";
    //      工程团支部团员发展
    public static final String ERBDLM= "EngineeringRegimentBranchDevelopmentLeagueMembers.html?";
    //      工程团支部团务公开
    public static final String ERBPA= "EngineeringRegimentBranchPublicAffairs.html?";
    //      专题教育
    public static final String TE= "SpecialColumnK.html?";
    //      视频教育
    public static final String VE= "VideoEducation.html?";
    //      书香宁电
//    public static final String BF= "BookFragrant.html?";
    public static final String BF= "BookTopic.html?";
    //      会议统计分析
    public static final String SASC= "StatisticalAnalySisConference.html?";
    //      转入转出统计分析
    public static final String PSSA= "PersonnelStatisticalAnalysis.html?";
    //      测试
    public static final String TEST= "test_test.html?";
    //      班组管理
    public static final String GM= "GroupManagement.html?";
    //      综合团课课件
    public static final String CZH= "Class_courseware_ZH.html?";
    //      经营团课课件
    public static final String CJY= "Class_courseware_JY.html?";
    //      工程团课课件
    public static final String CGC= "Class_courseware_GC.html?";
    //      设备团课课件
    public static final String CSB= "Class_courseware_SB.html?";
    //      运行团课课件
    public static final String CYX= "Class_courseware_YX.html?";
    //      综合党课课件
    public static final String LZH= "Lectures_courseware_ZH.html?";
    //      经营党课课件
    public static final String LJY= "Lectures_courseware_JY.html?";
    //     工程党课课件
    public static final String LGC= "Lectures_courseware_GC.html?";
    //      设备党课课件
    public static final String LSB= "Lectures_courseware_SB.html?";
    //      运行党课课件
    public static final String LYX= "Lectures_courseware_YX.html?";
    //     综合工会课件
    public static final String TZH= "Teaching_courseware_ZH.html?";
    //      经营工会课件
    public static final String TJY= "Teaching_courseware_JY.html?";
    //      工程工会课件
    public static final String TGC= "Teaching_courseware_GC.html?";
    //      设备工会课件
    public static final String TSB= "Teaching_courseware_SB.html?";
    //      运行工会课件
    public static final String TYX= "Teaching_courseware_YX.html?";
    //      综合管理部
    public static final String CD= "IntegratedDepartment.html?";
    //      团青推优
    public static final String YT= "Youth_Tuiyou.html?";
    //      综合党支部积极分子
    public static final String CPB0= "ComprehensivePartyBranchActivists.html?flag=0";
    //      经营党支部积极分子
    public static final String CPB1= "ComprehensivePartyBranchActivists.html?flag=1";
    //      工程党支部积极分子
    public static final String CPB2= "ComprehensivePartyBranchActivists.html?flag=2";
    //      设备党支部积极分子
    public static final String CPB3= "ComprehensivePartyBranchActivists.html?flag=3";
    //      运行党支部积极分子
    public static final String CPB4= "ComprehensivePartyBranchActivists.html?flag=4";
    //      综合党支部党员信息
    public static final String IMPB0= "InformationPartyMembersPartyBranch.html?flag=0";
    //      经营党支部党员信息
    public static final String IMPB1= "InformationPartyMembersPartyBranch.html?flag=1";
    //      工程党支部党员信息
    public static final String IMPB2= "InformationPartyMembersPartyBranch.html?flag=2";
    //      设备党支部党员信息
    public static final String IMPB3= "InformationPartyMembersPartyBranch.html?flag=3";
    //      运行党支部党员信息
    public static final String IMPB4= "InformationPartyMembersPartyBranch.html?flag=4";




















}
