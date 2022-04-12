package com.aetherwars.model;
import java.util.*;
import com.aetherwars.util.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class Gameplay {
    static Integer phase = 1;
    static Hashtable<String, List<String[]>> data;

    public static void loadAllFile(String[] fileName) {
    // F.S. Dictionary data terisi dengan semua file .csv dengan key adalah nama file
        data = new Hashtable<String, List<String[]>>();
        for(int i=0; i<fileName.length; i++) {
            try {
                Path data_csv_path = Paths.get(fileName[i]).toAbsolutePath();
                File data_csv = new File(data_csv_path.toString());
                CSVReader data_reader = new CSVReader(data_csv, "	");

                List<String[]> input_data = new ArrayList<String[]>();
                input_data = data_reader.read();

                data.put(fileName[i], input_data);
    
                // Iterator<String[]> row = input_data.iterator();
                // while (row.hasNext()) {
    
                //     System.out.println(Arrays.toString(row.next()));
                // }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Read error");
            }
        }
    }
    public static void main(String[] args) {
        String[] fileName = {"src/main/resources/com/aetherwars/card/data/character.csv", "src/main/resources/com/aetherwars/card/data/spell_morph.csv", "src/main/resources/com/aetherwars/card/data/spell_ptn.csv", "src/main/resources/com/aetherwars/card/data/spell_swap.csv"};
        loadAllFile(fileName);
    }
}
