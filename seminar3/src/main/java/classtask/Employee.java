package classtask;

public class Employee implements Comparable<Employee>{
    private String name;
    private String post;
    private String phonenumber;
    private Integer salary;
    private Integer birthyear;

    public Employee(String name, String post, String phonenumber, Integer salary, Integer birthyear) {
        this.name = name;
        this.post = post;
        this.phonenumber = phonenumber;
        this.salary = salary;
        this.birthyear = birthyear;
    }

    public Integer getAge(){
        return 2024 - birthyear;
    }

    @Override
    public String toString() {
        String age = getAge() + switch (getAge() % 10) {
            case 1 ->  " год";
            case 2, 3, 4 -> " года";
            default -> " лет";
        };
        return String.format("%-15s  %-19s  %-13s  %6s  %7s",
                name, birthyear + " г.р. (" + age + ")", post, salary, phonenumber);
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public static void increaseSalary (Employee empl, Integer summ){
        empl.salary = empl.salary + summ;
    }

    public String getPost() {
        return post;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    @Override
    //реализуем метод compareTo интерфейса Comparable
    public int compareTo(Employee o) {
        //используем метод compareTo из класса String для сравнения имен
        int result = this.getName().compareTo(o.getName());

        //если имена одинаковые - сравниваем возраст,
        if (result == 0) {
            result = this.getBirthyear() - o.getBirthyear();
        }
        return result;
    }
}
