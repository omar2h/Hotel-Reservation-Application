package util.printer;

import java.util.List;

public class MenuPrinter implements Printer {
    public void print(List<Object> items){
        System.out.println();
        System.out.println("----------------------------------");
        for(int i=0; i< items.size(); i++){
            System.out.println(i+1 + ". " + items.get(i));
        }
        System.out.println("----------------------------------");
        System.out.println("Please select a number for the menu option");

    }
}
