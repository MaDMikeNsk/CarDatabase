/*[M5_L5] Написать программу, которая будет выдавать имя владельца автомобиля по его номеру.
          Программа должна быть умной: если у неё в памяти номера нет, она должна попросить ввести его имя и запомнить.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap <String, String> carDatabase = new HashMap<>(); //<Номер авто, Имя владельца>
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputNumber = "";
        String inputName;
        String numberRegex = "(^[а-я][0-9]{3}[а-я]{2}[0-9]{2}$)|(^STOP$)"; //Шаблон номера или команда STOP
        String nameRegex = "^[А-Яа-я\\s]+$";                                //Шаблон имени (одиночное: Вася, Петя, ...)
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";

        while (!inputNumber.equals("STOP")) {
            do {
                System.out.println("Введите номер автомобиля или команду <STOP> для выхода из программы");
                inputNumber = reader.readLine().trim();
                if (!inputNumber.matches(numberRegex)) {
                    System.out.println("Неправильный формат ввода. Попробуйте ещё раз");
                }
            } while (!inputNumber.matches(numberRegex));

            if (!inputNumber.equals("STOP")) {
                if (carDatabase.containsKey(inputNumber)) {
                    System.out.println("Автомобиль с номером " + ANSI_BLUE + inputNumber + ANSI_RESET +
                            " принадлежит владельцу по имени " + ANSI_BLUE + carDatabase.get(inputNumber) + ANSI_RESET);
                } else {
                    System.out.println("Автомобиль в базе не зарегистрирован. Для регистрации введите имя владельца:");
                    do {
                        inputName = reader.readLine().trim();
                        if (!inputName.matches(nameRegex)) {
                            System.out.println("Неправильный формат ввода. Попробуйте ещё раз");
                        }
                    } while (!inputName.matches(nameRegex));
                    carDatabase.put(inputNumber, inputName);
                    System.out.println("Автомобиль с номером " + ANSI_RED + inputNumber + ANSI_RESET +
                            " добавлен в базу. Владелец: " + ANSI_RED + inputName + ANSI_RESET);
                }
            } else {
                System.out.println("Программа закончила работу. До свидания!");
            }
        }
    }
}