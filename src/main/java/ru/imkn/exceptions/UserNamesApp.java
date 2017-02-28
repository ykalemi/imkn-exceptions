package ru.imkn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by ykalemi on 28.02.2017.
 */
public class UserNamesApp {

    private static final Logger LOG = LoggerFactory.getLogger(UserNamesApp.class);

    public static class UserInfo {
        private final String name;
        private final int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("name: %s; age: %d", name, age);
        }
    }

    public static class UserStorage {
        private static final UserStorage INSTANCE = new UserStorage();
        public static UserStorage getInstance() { return INSTANCE;}

        private List<UserInfo> users;

        public void addUser(UserInfo user) {
            users.add(user);
        }

        public List<UserInfo> getUsers() {
            return new ArrayList<>(users);
        }
    }

    public static void main(String args[]) {
        UserStorage storage = UserStorage.getInstance();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Optional<String> valueOpt = read("Enter user name (enter 'q' to exit): ", scanner);
                if (!valueOpt.isPresent()) {
                    break;
                }
                String userName = valueOpt.get();

                valueOpt = read("Enter user age (enter 'q' to exit): ", scanner);
                if (!valueOpt.isPresent()) {
                    break;
                }
                int age = Integer.valueOf(valueOpt.get());

                UserInfo user = new UserInfo(userName, age);

                // TODO: validate me!

                storage.addUser(user);
                LOG.debug("User was added: {}", user);
            }

            // TODO: save me to a file!
            //saveToFile(storage, scanner);
        }
    }

    public static Optional<String> read(String question, Scanner scanner) {
        System.out.print(question);

        String value = scanner.hasNext() ? scanner.next() : null;
        if ("q".equals(value)) {
            value = null;
        }
        return Optional.ofNullable(value);
    }

/*    private static void saveToFile(UserStorage storage, Scanner scanner) {
        Optional<String> fileNameOpt = read("eneter filename to save users", scanner);

        if (!fileNameOpt.isPresent()) {
            return;
        }

        File file = new File(fileNameOpt.get());
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileOutputStream stream = new FileOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream))) {
            storage.getUsers().stream().forEach(userInfo -> {
                writer.write(userInfo.toString());
                writer.newLine();
                writer.flush();
            });
        }
    }*/
}
