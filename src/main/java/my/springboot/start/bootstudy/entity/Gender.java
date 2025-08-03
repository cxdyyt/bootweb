package my.springboot.start.bootstudy.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Gender {
    MALE(1, "男"), FEMALE(2, "女");

    @EnumValue
    private final int value;
    private final String sex;

    Gender(int value, String sex) {
        this.value = value;
        this.sex = sex;
    }

    public int getValue() {
        return value;
    }

    public String getSex() {
        return sex;
    }
}
