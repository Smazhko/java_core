package web_shop;

import java.util.ArrayList;

public class Client implements Comparable<Client> {
    private Integer id;
    private String name;
    private static Integer newId = 100;
    private Gender gender;

    public enum Gender {
        MALE("М"),
        FEMALE("Ж");

        private String name;

        Gender(String name){
            this.name = name;
        }

        public String getGender() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    };


    public Client(String name, Gender gender) {
        id = newId++;
        this.gender = gender;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Client o) {
        return o.id - this.id;
    }

    @Override
    public String toString() {
        return "Клиент: id " + id + ", " + name + " (" + gender.toString() + ")";
    }


}
