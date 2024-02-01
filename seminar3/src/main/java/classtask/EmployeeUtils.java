package classtask;

public class EmployeeUtils {


    public static void increaseSalaryByAge (Employee empl, Integer age, Integer summ) {
        if (empl.getAge() > age)
            empl.setSalary(empl.getSalary() + summ);
    }

    public static Integer countAverageAge(Employee[] emplList){
        int totalAge = 0;
        for(Employee empl: emplList){
            totalAge += empl.getAge();
        }
        return totalAge / emplList.length;
    }

    public static Integer countAverageSalary(Employee[] emplList){
        int totalSalary = 0;
        for(Employee empl: emplList){
            totalSalary += empl.getSalary();
        }
        return totalSalary / emplList.length;
    }

    public static void increaseSalaryToAll (Employee[] empList, int age, int summ){
        for(Employee empl: empList){
            EmployeeUtils.increaseSalaryByAge(empl, age, summ);
        }
        System.out.println("\nПовышение ЗП сотрудникам старше " + age + " на " + summ + "...\n");
    }

    public static void increaseSalaryToAllExceptChief (Employee[] empList, int age, int summ){
        for(Employee empl: empList){
            Chief.increaseSalaryByAge(empl, age, summ);
        }
        System.out.println("\nПовышение директором ЗП сотрудникам старше " + age + " на " + summ + "...\n");
    }

    public static void printEmplList(Employee[] empList){
        for(Employee item: empList) {
            System.out.println(item);
        }
    }


}
