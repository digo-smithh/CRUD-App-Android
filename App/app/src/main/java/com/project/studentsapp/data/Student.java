package com.project.studentsapp.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.*;

import java.util.Objects;

public class Student {

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("email")
    private String email;

    public Student (String code, String name, String email) throws Exception {
        setCode(code);
        setName(name);
        setEmail(email);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setCode(String code) throws Exception {
        if (code.length() != 5)
            throw  new  Exception("Code must be 5 characters");

        this.code = code;
    }

    public void setName(String name) throws Exception {
        if (name.length() > 50)
            throw  new  Exception("Name must not be more than 50 characters");

        this.name = name;
    }

    public void setEmail(String email) throws Exception {
        if (email.length() > 50)
            throw  new  Exception("Email must not be more than 50 characters");

        this.email = email;
    }

    @Override
    public String toString() {
        return  "Code..........." + code + "\n" +
                "Name..........." + name + "\n" +
                "Email.........." + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        return Objects.equals(code, student.code) &&
                Objects.equals(name, student.name) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, email);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
