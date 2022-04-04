package model;

// Класс для работы с общим рейтингом
public class Rating {

    private String NickName; // Имя игрока
    private int Win; // Кол-во побед
    private int Lose; // Кол-во поражений
    private int DeadHeat; // Кол-во ничьих

    public Rating(String NickName, int Win, int Lose, int DeadHeat) {
        this.NickName = NickName;
        this.Win = Win;
        this.Lose = Lose;
        this.DeadHeat = DeadHeat;
    }

    // Геттеры и сеттеры


    public String getNickName() {
        return NickName;
    }

    public int getWin() {
        return Win;
    }

    public void setWin(int win) {
        Win = win;
    }

    public int getLose() {
        return Lose;
    }

    public void setLose(int lose) {
        Lose = lose;
    }

    public int getDeadHeat() {
        return DeadHeat;
    }

    public void setDeadHeat(int deadHeat) {
        DeadHeat = deadHeat;
    }

}
