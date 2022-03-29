package HelpMethods;

public class PrintField {

    // Возращает поле текущей игры в формате 1 строки
    public static String PrintField(String[] field){
        return ( " | "+field[0]+" | "+field[1]+" | "+field[2]+" | <br>" +
                " | "+field[3]+" | "+field[4]+" | "+field[5]+" | <br>" +
                " | "+field[6]+" | "+field[7]+" | "+field[8]+" |");
    }
}
