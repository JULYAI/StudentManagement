package net.student.bean.bean;

public class Student {
    //学号
    private String id;
    //姓名
    private String name;
    //性别
    private String sex;
    //课程
    private String myCourses;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(String  myCourses) {
        this.myCourses = myCourses;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sex=" + sex
                + ", courses=" + myCourses + "]";
    }
}
