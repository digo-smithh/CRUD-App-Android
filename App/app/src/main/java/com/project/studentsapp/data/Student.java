package com.project.studentsapp.data;

public class Student {

    private String code;
    private String name;
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
}
