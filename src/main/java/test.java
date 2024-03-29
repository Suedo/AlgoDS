import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    // n * n
    
    private static String path = "Files/in/in/transpose.txt";
    
    public ArrayList<ArrayList<String>> transpose(ArrayList<ArrayList<String>> matrix) {
        ArrayList<ArrayList<String>> t = new ArrayList<>();
        
        for (int i = 0; i < matrix.get(0).size(); i++) {
            t.add(new ArrayList<String>());
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                t.get(i).add(matrix.get(j).get(i));
            }
        }

        return t;
    }

    @Test
    void char_toString_ignores_nulls() {
        char[] arr = new char[10];
        arr[0] = 'a';
        arr[1] = 'b';

        System.out.println(new String(arr).trim());

    }


    public static void main(String[] args) {

        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {

            String line = "";
            while ((line = r.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
                matrix.add(row);
            }
    
            ArrayList<ArrayList<String>> op = new test().transpose(matrix);
            System.out.println(op);
    
    
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
    
    
}

// matrices

// row * col
//n * m
// m * n
