package web_shop;

/*
1.  Эмуляция интернет-магазина:
    написать классы покупатель, товар и заказ;
    создать массив покупателей, массив товаров и массив заказов;
    создать статический метод “совершить покупку” со строковыми параметрами, соответствующими полям объекта заказа.
    Метод должен вернуть объект заказа
    Если в метод передан несуществующий покупатель, товар или отрицательное количество,
    метод должен выбросить соответствующее исключение (кастомное с информативным сообщением);
    Вызвать метод совершения покупки несколько раз таким образом,
    чтобы заполнить массив покупок возвращаемыми значениями. Обработать исключения.
    Массив заказов записыва в файл (1 заказ - 1 строка).
    Вывести в консоль итоговое количество совершённых покупок после выполнения приложения.
2*. В класс покупателя добавить перечисление с гендерами, добавить в покупателя свойство «пол» со значением
    созданного перечисления. Добавить геттеры, сеттеры.
*/

import web_shop.custom_exceptions.NonExistClientException;
import web_shop.custom_exceptions.NonExistProductException;
import web_shop.custom_exceptions.WrongCountException;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopApp {
    public static ArrayList<Client> clientList = new ArrayList<>();

    public static ArrayList<Product> productList = new ArrayList<>();

    public static ArrayList<Order> orderList = new ArrayList<>();

    public static void main(String[] args) {
        clientList.addAll(
                Arrays.asList(
                    new Client("Сергей", Client.Gender.MALE),
                    new Client("Мария", Client.Gender.FEMALE),
                    new Client("Алекандр", Client.Gender.MALE),
                    new Client("Зухра", Client.Gender.FEMALE),
                    new Client("Эмбер", Client.Gender.FEMALE)
                )
        );
        productList.addAll(
                Arrays.asList(
                        new Product("Кроссовки", 5_000.0, 10),
                        new Product("Пальто", 15_800.0, 5),
                        new Product("Шапка", 2_100.0, 15),
                        new Product("Перчатки", 1_500.0, 4),
                        new Product("Джемпер", 3_400.0, 8),
                        new Product("Ремень", 3_200.0, 5),
                        new Product("Джинсы", 4_800.0, 12)
                )
        );
        orderList.addAll(
                Arrays.asList(
                        new Order(clientList.get(0), productList.get(2), 1, "оплачен"),
                        new Order(clientList.get(2), productList.get(4), 2, "не оплачен")
                )
        );


        System.out.println("---- НАЧАЛЬНЫЕ ЗНАЧЕНИЯ ----");
        printList(clientList);
        printList(productList);
        printList(orderList);

        System.out.println("---- ЗАКАЗ ----");
        System.out.println(makeOrder(102, 303, 1));

        System.out.println(makeOrder(99, 303, 1));
        System.out.println(makeOrder(102, 312, 1));
        System.out.println(makeOrder(102, 303, -2));
        System.out.println(makeOrder(102, 303, 12));
        System.out.println(makeOrder(100, 304, 4));
        System.out.println(makeOrder(104, 304, 4));

        System.out.println("---- все заказы ----");
        printList(orderList);
        System.out.println("---- все товары ----");
        printList(productList);

    }



    public static void printList(ArrayList list){
        for(Object item: list){
            System.out.println(item.toString());
        }
    }



    public static Product makeOrder(Integer clientId, Integer productId, Integer currCount) {
        Client currClient = clientList
                .stream()
                .filter(cli -> (cli.getId().equals(clientId)))
                .findFirst()
                .orElse(null);

        Product currProduct = productList
                .stream()
                .filter(prod -> (prod.getId().equals(productId)))
                .findFirst()
                .orElse(null);

        try {
            if (currClient == null) {
                throw new NonExistClientException();
            }
            if (currProduct == null) {
                throw new NonExistProductException();
            }
            if (currCount <= 0) {
                throw new WrongCountException("количество товара в заказе (" + currCount +
                        ") меньше или равно нулю.");
            }
            if (currCount > currProduct.getCount()) {
                throw new WrongCountException("количество товара в заказе " + currCount +
                        " превышает объём остатков товара(" + currProduct.getCount() + ").");
            }
            Order currOrder = new Order(currClient, currProduct, currCount, "не оплачен");
            orderList.add(currOrder);
            currProduct.decreaseCount(currCount);
        } catch (NonExistClientException | NonExistProductException | WrongCountException e) {
            System.out.println("\u001B[93m" + e.getMessage() + "\u001B[0m");
            return null;
        }
        return currProduct;
    }

}

/*
---- НАЧАЛЬНЫЕ ЗНАЧЕНИЯ ----
Клиент: id 100, Сергей (М)
Клиент: id 101, Мария (Ж)
Клиент: id 102, Алекандр (М)
Клиент: id 103, Зухра (Ж)
Клиент: id 104, Эмбер (Ж)
Товар: id 300, Кроссовки, цена 5000.0p, кол-во 10 шт.
Товар: id 301, Пальто, цена 15800.0p, кол-во 5 шт.
Товар: id 302, Шапка, цена 2100.0p, кол-во 15 шт.
Товар: id 303, Перчатки, цена 1500.0p, кол-во 4 шт.
Товар: id 304, Джемпер, цена 3400.0p, кол-во 8 шт.
Товар: id 305, Ремень, цена 3200.0p, кол-во 5 шт.
Товар: id 306, Джинсы, цена 4800.0p, кол-во 12 шт.
Заказ: id: 500, Клиент: id 100, Сергей (М) || Товар: id 302, Шапка, цена 2100.0p, кол-во 15 шт., заказано 1 шт., ВСЕГО 2100.0р. || статус - оплачен
Заказ: id: 501, Клиент: id 102, Алекандр (М) || Товар: id 304, Джемпер, цена 3400.0p, кол-во 8 шт., заказано 2 шт., ВСЕГО 6800.0р. || статус - не оплачен
---- ЗАКАЗ ----
Товар: id 303, Перчатки, цена 1500.0p, кол-во 3 шт.
<<  !  >> ОШИБКА: КЛИЕНТ не найден.
null
<<  !  >> ОШИБКА: ТОВАР не найден.
null
<<  !  >> ОШИБКА: Неверное количество товара в заказе: количество товара в заказе (-2) меньше или равно нулю.
null
<<  !  >> ОШИБКА: Неверное количество товара в заказе: количество товара в заказе 12 превышает объём остатков товара(3).
null
Товар: id 304, Джемпер, цена 3400.0p, кол-во 4 шт.
Товар: id 304, Джемпер, цена 3400.0p, кол-во 0 шт.
---- все заказы ----
Заказ: id: 500, Клиент: id 100, Сергей (М) || Товар: id 302, Шапка, цена 2100.0p, кол-во 15 шт., заказано 1 шт., ВСЕГО 2100.0р. || статус - оплачен
Заказ: id: 501, Клиент: id 102, Алекандр (М) || Товар: id 304, Джемпер, цена 3400.0p, кол-во 0 шт., заказано 2 шт., ВСЕГО 6800.0р. || статус - не оплачен
Заказ: id: 502, Клиент: id 102, Алекандр (М) || Товар: id 303, Перчатки, цена 1500.0p, кол-во 3 шт., заказано 1 шт., ВСЕГО 1500.0р. || статус - не оплачен
Заказ: id: 503, Клиент: id 100, Сергей (М) || Товар: id 304, Джемпер, цена 3400.0p, кол-во 0 шт., заказано 4 шт., ВСЕГО 13600.0р. || статус - не оплачен
Заказ: id: 504, Клиент: id 104, Эмбер (Ж) || Товар: id 304, Джемпер, цена 3400.0p, кол-во 0 шт., заказано 4 шт., ВСЕГО 13600.0р. || статус - не оплачен
---- все товары ----
Товар: id 300, Кроссовки, цена 5000.0p, кол-во 10 шт.
Товар: id 301, Пальто, цена 15800.0p, кол-во 5 шт.
Товар: id 302, Шапка, цена 2100.0p, кол-во 15 шт.
Товар: id 303, Перчатки, цена 1500.0p, кол-во 3 шт.
Товар: id 304, Джемпер, цена 3400.0p, кол-во 0 шт.
Товар: id 305, Ремень, цена 3200.0p, кол-во 5 шт.
Товар: id 306, Джинсы, цена 4800.0p, кол-во 12 шт.

Process finished with exit code 0
 */
