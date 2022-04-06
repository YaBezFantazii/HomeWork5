package model;

import exceptions.PlayerNickLengthException;
import exceptions.RatingStatisticException;

// Класс для работы с общим рейтингом
public class Rating {

    private String nickName; // Имя игрока
    private int win; // Кол-во побед
    private int lose; // Кол-во поражений
    private int deadHeat; // Кол-во ничьих

    public Rating(String nickName, int win, int lose, int deadHeat) throws PlayerNickLengthException, RatingStatisticException {
        if (nickName.length()>30){
            // Имя игрока не может быть больше 30 символов (ограничение БД, там стоит тип данных varchar30)
            throw new PlayerNickLengthException("Слишком длинное имя игрока (больше 30 символов)");
        } else if (nickName.trim().length()==0){
            // Имя игрока не может быть пустым
            throw new PlayerNickLengthException("Имя пустое");
            // Кол-во побед, поражений или ничьих не может быть меньше 0
        } else if (win<0||lose<0||deadHeat<0) {
            throw new RatingStatisticException("Значения некорректны. ",win,lose,deadHeat);
        } else {
            this.nickName = nickName;
            this.win = win;
            this.lose = lose;
            this.deadHeat = deadHeat;
        }
    }

    // Геттеры и сеттеры

    public String getNickName() {
        return nickName;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) throws RatingStatisticException{
        if (win>=0) {
            this.win = win;
        } else {
            throw new RatingStatisticException("Кол-во побед некорректно (<0) : ",win);
        }
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) throws RatingStatisticException{
        if (lose>=0) {
            this.lose = lose;
        } else {
            throw new RatingStatisticException("Кол-во поражений некорректно (<0) : ",lose);
        }
    }

    public int getDeadHeat() {
        return deadHeat;
    }

    public void setDeadHeat(int deadHeat) throws RatingStatisticException{
        if (deadHeat>=0) {
            this.deadHeat = deadHeat;
        } else {
            throw new RatingStatisticException("Кол-во ничьих некорректно (<0) : ",deadHeat);
        }
    }

}
