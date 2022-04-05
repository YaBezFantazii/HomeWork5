package services;

import utils.PrintField;
import model.GameList;

public class PrintGame {

    // Печать поля текущего поля игры, полученного из объекта GameList (от PrintArchive отличает тем,
    // что печатает текущей ход, а не все ходы, и имя текущего игрока.
    public static String PrintGame(GameList GameList) {
        String print = "";
        String filed[] = new String[]{"-","-","-","-","-","-","-","-","-"};
        // Так как игроки всегда ходят поочередно, 1 игрок всегда Х, 2 всегда 0,
        // то xml.getCellId[i] - массив int, где i[0,2,4..четное] - ходы 1 игрока, i = нечетное - ходы 2 игрока
        // i=1,2,3... - соответствует порядку ходов
        if (GameList.getCell().size()%2==0) {
            print = "Ход игрока 1 (X) : "+GameList.getNickName1() +"<br>";
        } else {
            print = "Ход игрока 2 (O) : "+GameList.getNickName2() +"<br>";
        }
        for (int i=0;i<GameList.getCell().size();i++){
            if ((i%2)==0) {
                filed[GameList.getCellId(i)-1] = "X";
            } else {
                filed[GameList.getCellId(i)-1] = "O";
            }
        }
        print += PrintField.PrintField(filed)+"<br>"+"<br>";
        return print;
    }

}
