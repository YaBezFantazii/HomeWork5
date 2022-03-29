package HelpMethods;

import Class.GameList;

public class PrintArchive {

    // Метод, возращающий ходы игры, полученные из объекта Class.GameList
    public static String Print(GameList GameList){

        String print = "";
        String filed[] = new String[]{"1","2","3","4","5","6","7","8","9"};
        // Так как игроки всегда ходят поочередно, 1 игрок всегда Х, 2 всегда 0,
        // то xml.getCellId[i] - массив int, где i[0,2,4..четное] - ходы 1 игрока, i = нечетное - ходы 2 игрока
        // i=1,2,3... - соответствует порядку ходов
        for (int i=0;i<GameList.getCell().size();i++){
            if ((i%2)==0) {
                print += "Ход игрока 1 (X) : "+GameList.getNickName1() +"<br>";
                filed[GameList.getCellId(i)-1] = "X";
                print += PrintField.PrintField(filed)+"<br>"+"<br>";
            } else {
                print += "Ход игрока 2 (O) : "+GameList.getNickName2() +"<br>";
                filed[GameList.getCellId(i)-1] = "O";
                print += PrintField.PrintField(filed)+"<br>"+"<br>";
            }
        }

        if (GameList.getWin()==0) {
            print += "Player 1 -> " +GameList.getNickName1()+ " is winner as 'X'!<br>";
        } else if (GameList.getWin()==1){
            print += "Player 2 -> " +GameList.getNickName2()+ " is winner as 'O'!<br>";
        } else {
            print += "Draw!<br>";
        }
        return print;
    }

}
