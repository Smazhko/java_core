package classtask;

public class Chief extends Employee{

    public Chief(String name, String phonenumber, Integer salary, Integer birthyear) {
        super(name, "директор", phonenumber, salary, birthyear);
    }

    public static void increaseSalaryByAge (Employee empl, Integer age, Integer summ) {
        if (empl.getAge() > age && !empl.getPost().equals("директор"))
            empl.setSalary(empl.getSalary() + summ);
    }
}
