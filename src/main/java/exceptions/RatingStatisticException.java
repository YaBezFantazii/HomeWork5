package exceptions;

// содержит исключения связанные с классом Rating, переменными win,lose,deadhead, чтобы они не были отрицательными.

public class RatingStatisticException extends Exception{

    public RatingStatisticException (String error, int num){
        super(error+num);
    }

    public RatingStatisticException (String error, int win, int lose, int deadhead){
        super(error);
        String errors = "Значения некорректны. ";
        if (win<0) {
            errors+="Кол-во побед некоретно : "+win+". ";
        }
        if (lose<0) {
            errors+="Кол-во поражений некоретно : "+lose+". ";
        }
        if (deadhead<0) {
            errors+="Кол-во ничьих некоретно : "+deadhead+". ";
        }
        System.err.println(errors);
    }
}
