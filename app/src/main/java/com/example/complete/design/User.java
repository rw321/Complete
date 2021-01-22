package com.example.complete.design;

public class User {

    private final String name;

    private final int sex;

    private int age;

    private String phone;

    private String address;

    private User(Builder builder) {
        name = builder.name;
        sex = builder.sex;
        age = builder.age;
        phone = builder.phone;
        address = builder.address;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class Builder {

        private final String name;

        private final int sex;

        private int age;

        private String phone;

        private String address;

        public Builder(String name , int sex) {
            this.name = name;
            this.sex = sex;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
