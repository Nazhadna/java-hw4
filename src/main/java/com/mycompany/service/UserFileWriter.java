package com.mycompany.service;

import com.mycompany.model.User;

import java.io.FileWriter;
import java.io.IOException;

public class UserFileWriter {

    public void writeToFile(User user) {
        try(FileWriter writer = new FileWriter("userlist.txt", true))
        {
            writer.write(user.toString()+'\n');
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
