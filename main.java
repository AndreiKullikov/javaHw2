import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class UserData {
    String lastName;
    String firstName;
    String middleName;
    String birthDate;
    long phoneNumber;
    char gender;

    UserData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}

public class UserDataApp {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол), разделенные пробелом:");
            String input = scanner.nextLine();

            String[] inputData = input.split("\\s+");

            if (inputData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все 6 параметров.");
            }

            String lastName = inputData[0];
            String firstName = inputData[1];
            String middleName = inputData[2];
            String birthDate = inputData[3];
            long phoneNumber = Long.parseLong(inputData[4]);
            char gender = inputData[5].charAt(0);

            validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            writeToFile(userData);

            System.out.println("Данные успешно записаны в файл.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        if (lastName.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || birthDate.isEmpty()) {
            throw new IllegalArgumentException("Неверный формат данных. Заполните все обязательные поля.");
        }

      
    }

    private static void writeToFile(UserData userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userData.lastName + ".txt", true))) {
            writer.write(userData.lastName + userData.firstName + userData.middleName +
                    userData.birthDate + " " + userData.phoneNumber + userData.gender);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
