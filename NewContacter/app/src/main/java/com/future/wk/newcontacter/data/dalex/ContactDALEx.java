package com.future.wk.newcontacter.data.dalex;

import android.database.Cursor;
import android.text.TextUtils;

import com.future.wk.newcontacter.base.store.db.BaseDB;
import com.future.wk.newcontacter.base.store.db.SqliteBaseDALEx;
import com.future.wk.newcontacter.base.store.db.annotation.DatabaseField;
import com.future.wk.newcontacter.base.store.db.annotation.DatabaseField.FieldType;
import com.future.wk.newcontacter.base.store.db.annotation.SqliteDao;
import com.future.wk.newcontacter.util.CorePinYinUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *  联系人列表
 */

public class ContactDALEx extends SqliteBaseDALEx {

    public static final String USERID = "userid";
    public static final String USERNAME = "username";
    public static final String DEPTNAME = "deptname";
    public static final String NAMEPINYIN = "namepinyin";
    public static final String USERPHONE = "userphone";
    public static final String RECSTATUS = "recstatus";

    public static final String From_Contact = "From_Contact";


    @DatabaseField(Type = FieldType.INT,primaryKey = true)
    private int userid; //e号

    @DatabaseField(Type = FieldType.VARCHAR)
    private String username; //姓名

    @DatabaseField(Type = FieldType.VARCHAR)
    private String usericon; // 头像

    @DatabaseField(Type = FieldType.VARCHAR)
    private String userphone; // 手机号码

    @DatabaseField(Type = FieldType.VARCHAR)
    private String userjob; // 职业

    @DatabaseField(Type = FieldType.VARCHAR)
    private String useremail; // 电子邮箱

    @DatabaseField(Type = FieldType.INT)
    private int usersex; // 性别

    @DatabaseField(Type = FieldType.VARCHAR)
    private String namepinyin; // 名字拼音

    @DatabaseField(Type = FieldType.VARCHAR)
    private String usertel; // 固话

    public static ContactDALEx get(){
        return SqliteDao.getDao(ContactDALEx.class);
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserjob() {
        return userjob;
    }

    public void setUserjob(String userjob) {
        this.userjob = userjob;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getUsersex() {
        return usersex;
    }

    public void setUsersex(int usersex) {
        this.usersex = usersex;
    }

    public String getNamepinyin() {
        return namepinyin;
    }

    public void setNamepinyin(String namepinyin) {
        this.namepinyin = namepinyin;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    /**
     * @Desc 通过一组id查询通讯录
     **/
    public List<ContactDALEx> queryByUserIds(String userids) {
        String[] ms = userids.split(",");
        List<ContactDALEx> contacts = new ArrayList<>();
        for(String m:ms){
            ContactDALEx dalex = ContactDALEx.get().findById(m);
            if(dalex != null){
                contacts.add(dalex);
            }
        }
        return contacts;
    }

    public List<ContactDALEx> queryBySearch(String content) {
        return queryBySearch(content,"","");
    }

    /**
     * @Desc  从特定集合in中查找
     **/
    public List<ContactDALEx> queryBySearch(String content,String in) {
        return queryBySearch(content,in,"");
    }

    /***
     * @Desc  集合不能包括 notin
     **/
    public List<ContactDALEx> queryOtherBySearch(String content,String notin) {
        return queryBySearch(content,"",notin);
    }

    /**
     * 根据各种条件查找
     *
     * @param searchContent,ids
     * @return
     */
    public List<ContactDALEx> queryBySearch(String searchContent, String in, String notIn) {

        //集合中不能有的结果
        String notInIds = TextUtils.isEmpty(notIn)?"":USERID + " not in ("+notIn+") and ";

        //从集合中搜索
        String inIds = TextUtils.isEmpty(in)?"":USERID + " in ("+in+") and ";

        //搜索sql语句
        String search = "("+ USERNAME +" like '%"+searchContent+"%' or "
                + DEPTNAME +" like '%"+searchContent+"%' or "
                + NAMEPINYIN +" like '%"+searchContent+"%' or "
                + USERPHONE +" like '%"+searchContent+"%')  ";

        //排序
        String orderBy = " ORDER BY "+ NAMEPINYIN + " COLLATE NOCASE";

        List<ContactDALEx> result = new ArrayList<ContactDALEx>();
        Cursor cursor = null;
        try {
            BaseDB db = getDB();
            if (db.isTableExits(TABLE_NAME)) {
                String sql = "select * from "+ TABLE_NAME +" where "
                        + inIds
                        + notInIds
                        + search
                        + orderBy;
                cursor = db.find(sql, new String[] {});
                List<ContactDALEx> otherAlphaList = new ArrayList<ContactDALEx>();
                List<ContactDALEx> list = new ArrayList<ContactDALEx>();
                while (cursor != null && cursor.moveToNext()) {
                    ContactDALEx contact = new ContactDALEx();
                    contact.setAnnotationField(cursor);
                    if(!TextUtils.isEmpty(contact.getNamepinyin())){
                        contact.setNamepinyin(contact.getNamepinyin().toUpperCase());
                    }
                    if (CorePinYinUtil.getPinyinFirstAlpha(contact.getNamepinyin()).equals("#")) {
                        // 该通信录为自己，不显示
                        otherAlphaList.add(contact);
                    } else {
                        list.add(contact);
                    }
                }

                result.addAll(list);
                result.addAll(otherAlphaList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return result;
    }
}
