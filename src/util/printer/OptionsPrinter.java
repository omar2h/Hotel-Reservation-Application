package util.printer;

import java.util.List;

public class OptionsPrinter implements Printer {
    public void print(List<Object> items){
        System.out.println("----------------------------------");
        for(int i=0; i< items.size(); i++){
            System.out.println(i+1 + ". " + items.get(i));
        }
        System.out.println("----------------------------------");
    }
}
