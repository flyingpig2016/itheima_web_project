package com.itheima.pojo;

import lombok.*;

/**
 * @Author: flyingpig
 * @Date: 2023-04-09-23:55
 * @Description:
 */
//@Getter  //1
//@Setter  //2
//@ToString //3
//@EqualsAndHashCode //4
@NoArgsConstructor  //无参构造
@AllArgsConstructor //全参构造
@Data  //相当于1234个注解
public class User {
    private Integer id;
    private String name;
    private Short age;
    private Short gender;
    private String phone;

//    public User() {
//    }
//
//    public User(Integer id, String name, Short age, Short gender, String phone) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.phone = phone;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Short getAge() {
//        return age;
//    }
//
//    public void setAge(Short age) {
//        this.age = age;
//    }
//
//    public Short getGender() {
//        return gender;
//    }
//
//    public void setGender(Short gender) {
//        this.gender = gender;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", age = '" + age + '\'' +
//                ", gender= " + gender +
//                ", phone= '" + phone + '\'' +
//                '}';
//    }
}
