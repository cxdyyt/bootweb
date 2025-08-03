package my.springboot.start.bootstudy.entity;

import com.baomidou.mybatisplus.annotation.*;

@TableName("user") // 指定对应的数据库表名
public class User {
    @TableId(value = "id", type = IdType.AUTO) // 指定为主键字段
    private int id;
    @TableField("name")
    String name;
    @TableField(value = "sex")
    Gender sex;
    int age;
    @TableLogic
    int isDel;
    String password;

    public User(int id, String name, int age, String password) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }
    public Gender getGender() {
        return sex;
    }

    public void setGender(Gender gender) {
        this.sex = gender;
    }

    public User() {
    }

    /**
     * Get the id.
     * 
     * @return the specified value.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id to the specified value.
     * 
     * @param id the specified value.
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    /**
     * Get the name.
     * 
     * @return the specified value.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name to the specified value.
     * 
     * @param name the specified value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the age.
     * 
     * @return the specified value.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age to the specified value.
     * 
     * @param age the specified value.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the password.
     * 
     * @return the specified value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password to the specified value.
     * 
     * @param password the specified value.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
