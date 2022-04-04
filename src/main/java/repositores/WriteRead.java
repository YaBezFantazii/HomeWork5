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
        String Read(String nameFile);
    }

    // Наследуется классами ReadRating, отвечающими за чтение из файла общего рейтинга
    public interface Read {
        String Read();


    }








    }

