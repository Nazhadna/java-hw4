package com.mycompany.service;

import com.mycompany.model.User;

import java.io.*;
import java.util.Scanner;

public class UserFileWorker {

    private File file;

    public UserFileWorker(String path) {
        file = new File(path);
    }

    public User findUser(User searchingUser) {
        User user = null;

        try (Scanner in = new Scanner(file).useDelimiter("\n")) {
            String s;
            while (in.hasNext()) {
                s = in.next();
                if (s.contains("surname= " + searchingUser.getSurname() + ", name= " + searchingUser.getName() + ",")) {
                    Scanner fields = new Scanner(s).useDelimiter(" ");
                    while (fields.hasNext()) {
                        if ("patronymic=".compareTo(fields.next()) == 0) {
                            user = new User();
                            user.setName(searchingUser.getName());
                            user.setSurname(searchingUser.getSurname());
                            user.setPatronymic(fields.next().replaceFirst(".$", ""));
                            fields.next();
                            user.setAge(Integer.valueOf(fields.next().replaceFirst(".$", "")));
                            fields.next();
                            user.setSalary(Double.valueOf(fields.next().replaceFirst(".$", "")));
                            fields.next();
                            user.setEmail(fields.next().replaceFirst(".$", ""));
                            fields.next();
                            user.setJob(fields.next());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        }
        return user;
    }

    public void uploadUsers(String pathFrom) throws IOException {

        FileReader fr = new FileReader(pathFrom);
        FileWriter fw = new FileWriter(file, true);
        int c = fr.read();
        while(c!=-1) {
            fw.write(c);
            c = fr.read();
        }
        fr.close();
        fw.close();
    }
}
