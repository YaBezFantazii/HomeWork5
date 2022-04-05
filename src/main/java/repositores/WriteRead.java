package repositores;

import model.GameList;

public class WriteRead {

    // Наследуется классами WriteJSON, WriteXML, отвечающими за запись в файлы xml и json
    public interface WriteFile {
        String Write(GameList GameList);
    }

    // Наследуется классами WriteBD, WriteRating отвечающими за запись в бд , запись в файл
    public interface Write {
        void Write(GameList GameList);
    }

    // Наследуется классами ReadJSON, ReadXML, ReadBD отвечающим за чтение из файлов xml, json и чтения из БД
    public interface ReadFile {
        GameList Read (String nameFile);
    }

    // Наследуется классами ReadRating,ReadRatingBD,, отвечающими за чтение общего рейтинга из файла result.txt и бд
    public interface Read {
        String Read();


    }








    }

