package exceptions;

// содержит исключения связанные с длинной имен игроков в классах GameList и Rating
public class PlayerNickLengthException extends Exception{

    public PlayerNickLengthException (String e) {
        super(e);
    }

}
