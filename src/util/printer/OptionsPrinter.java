package util.printer;

import java.util.List;

public class OptionsPrinter implements Printer {
    public void print(List<Object> items){
        for(int i=0; i< items.size(); i++){
            System.out.println(i+1 + ". " + items.get(i));
        }
    }
}
