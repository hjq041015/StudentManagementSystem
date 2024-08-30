package model;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name , int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() {

    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
