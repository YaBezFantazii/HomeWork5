package exceptions;

import java.util.ArrayList;

// содержит исключения связанный с данными в массиве cell (поле игры), и переменной win (кто победил/ничья) класса GameList
public class CellCheckException extends Exception{

    public CellCheckException (String error, int num){
        super(error+num);
    }

    public CellCheckException (String error, ArrayList<Integer> listNum){
        super(error+listNum);
    }

}
