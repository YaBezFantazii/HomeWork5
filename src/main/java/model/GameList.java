package model;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;

import java.util.ArrayList;

// Класс, объекты которого содержат данные об игре
public class GameList {
    private String NickName1; // Имя игрока 1
    private String NickName2; // Имя игрока 2
    private ArrayList<Integer> cell = new ArrayList<>(); // Ячейка, куда сделан ход. Номера элемента массива - порядок ходов
    private int win; // Кто победил: 0 - игрок 1, 1 - игрок 2, 2 - ничья, 3 - игра еще идет

    // Конструкторы
    public GameList() {
        this.win=3;
    }

    public GameList(String nickName1, String nickName2, ArrayList<Integer> cell, int win) throws PlayerNickLengthException, CellCheckException {
        String check;
        if ((check=CheckNickName(nickName1,nickName2))!=null) {
            throw new PlayerNickLengthException(check);
        } else if ((check=CheckCell(cell))!=""){
            throw new CellCheckException(check,cell);
        } else if (win>3||win<0) {
            throw new CellCheckException("Переменная win может быть от 0 до 3: ",win);
        } else {
            NickName1 = nickName1;
            NickName2 = nickName2;
            this.cell = cell;
            this.win = win;
        }
    }


    // геттеры и сеттеры
    public String getNickName1() {
        return NickName1;
    }

    public String getNickName2() {
        return NickName2;
    }

    public void setNickName(String nickName1, String nickName2) throws PlayerNickLengthException {
        String check;
        if ((check=CheckNickName(nickName1,nickName2))!=null) {
            throw new PlayerNickLengthException(check);
        } else {
            NickName1 = nickName1;
            NickName2 = nickName2;
        }
    }

    public ArrayList<Integer> getCell() {
        return cell;
    }

    public int getCellId (int i){
        return cell.get(i);
    }

    public void setCellId (int number) throws CellCheckException {
        boolean check = true;
        // Ячейки в поле игры могут быть от 1 до 9
        if (number>9 || number<1) {
            check = false;
            throw new CellCheckException("Введеное число за рамками возможных (от 1 до 9): ",number);
        }
        for (int i=0; i<this.cell.size();i++){
            // номера ячеек поля (данные в листе cell) не должны повторяться
            if (this.cell.get(i)==number) {
                check = false;
                throw new CellCheckException("Данная ячейка уже заполнена: ",number);
            }
        }
        if (check) {cell.add(number);}
    }

    public void setCell (ArrayList<Integer> steps) throws CellCheckException{
        String res;
        if ((res=CheckCell(steps))=="") {
            this.cell = steps;
        } else {
            throw new CellCheckException(res,steps);
        }
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) throws CellCheckException{
        if (win>3||win<0){
            throw new CellCheckException("Переменная win может быть от 0 до 3: ",win);
        } else {
            this.win = win;
        }
    }

    public void clearCell (){
        cell.clear();
    }

    // Проверка на корректность имен игроков
    public String CheckNickName (String nickName1, String nickName2) {
        if (nickName1.length()>30 || nickName2.length()>30){
            // Имя игрока не может быть больше 30 символов (ограничение таблицы БД, там стоит тип данных varchar30)
            return "Слишком длинное имя игрока (больше 30 символов)";
        } else if (nickName1.trim().length()==0 || nickName2.trim().length()==0){
            // Имя игрока не может быть пустым
            return "Одно или оба имена пусты";
        } else if (nickName1.equals(nickName2)){
            // Имена игроков не могу совпадать
            return "Имена совпадают";
        }
        return null;
    }

    // Проверка на заполнения массива cell
    public String CheckCell (ArrayList<Integer> steps) {
        if (steps.size()>9) {
            return "Массив больше 9 (больше кол-ва ячеек поля): ";
        }
        for (int i=0;i<steps.size();i++) {
            for (int g=i+1;g<steps.size();g++){
                if (steps.get(i)==steps.get(g)){
                    return "В массиве есть повторяющиеся данные (ячейки) ";
                }
            }
        }
        return "";
    }

}
