package task1;

/*
Задача:    Создать класс ”Сотрудник” с полями: ФИО, должность, телефон, зарплата, возраст;
Задача:   Написать функцию выводящую всю доступную информацию об объекте
*/



public class Emloyee {
    private String name;
    private String post;
    private String phonenumber;
    private Integer pay;
    private Integer age;

    public Worker(String name, String post, String phonenumber, Integer pay, Integer age) {
        this.name = name;
        this.post = post;
        this.phonenumber = phonenumber;
        this.pay = pay;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", pay=" + pay +
                ", age=" + age +
                '}';
    }
}
