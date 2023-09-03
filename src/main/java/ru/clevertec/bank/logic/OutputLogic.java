package ru.clevertec.bank.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The OutputLogic class is responsible for writing receipt data to a txt file.
 */
public class OutputLogic {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
    private final static File FILE_SOURCE = new File("check/Check" + dateFormat.format(new Date()) + ".txt");

    public static void inputInFile(List<String> check)  {
        try(FileWriter writer = new FileWriter(FILE_SOURCE)){
            PrintWriter print = new PrintWriter(writer);
            check.forEach(print::println);
            print.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
