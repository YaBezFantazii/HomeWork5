package repositores.rating;

import java.io.*;
import java.nio.charset.StandardCharsets;

import model.*;
import repositores.WriteRead;
import utils.Convert;

import java.util.ArrayList;
import java.util.Comparator;

public class WriteRating implements WriteRead.Write{

    // Метод для записи новых данных (после завершения игры) в общий рейтинг
    public void Write(GameList GameList) {
        int[] result = new int[]{0,0,0};
        if (GameList.getWin()==0){
            result[0]=1;
        } else if (GameList.getWin()==1) {
            result[1]=1;
        } else {
            result[2]=1;
        }

        // Добавляем данные о только что закоченной игре в массив объектов Rating
        ArrayList<Rating> Rating = new ArrayList<>();
        Rating.add(new Rating(
                GameList.getNickName1(),
                result[0],
                result[1],
                result[2]
        ));
        Rating.add(new Rating(
                GameList.getNickName2(),
                result[1],
                result[0],
                result[2]
        ));

        String a1;
        //Массив int, вспомогательный, для подсчета общего рейтинга
        int[] number = new int[]{0, 0, 0};
        //Вспомогательные массив строк, необходимый для записи в файл строк старых данных об играх.
        String line[] = new String[2];
        File file1 = new File("Result.txt");
        try {
            // Если файл Result.txt не создан, то создаем его
            if (!file1.exists() || file1.length() == 0) {
                BufferedWriter resultWrite1 = new BufferedWriter(new FileWriter("Result.txt", StandardCharsets.UTF_8));
                resultWrite1.write("Побед - поражений - ничьих\r\n\r\n");
                resultWrite1.flush();
                resultWrite1.close();
            }

            BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt", StandardCharsets.UTF_8));
            // Минимально проверяем файл result.txt на корректность, если неккорректен, то пересоздаем
            if (!resultRead.readLine().equals("Побед - поражений - ничьих")){
                BufferedWriter resultWrite1 = new BufferedWriter(new FileWriter("Result.txt", StandardCharsets.UTF_8));
                resultWrite1.flush();
                resultWrite1.close();
                resultRead.close();
            } else {
                resultRead.readLine();
                // Берем данные о рейтинге из файла Result.txt и записываем их в массив GameList
                while ((line[0] = resultRead.readLine()) != null) {
                    line[1] = resultRead.readLine();
                    resultRead.readLine();
                    a1 = line[0].substring(7);
                    number = Convert.Convert(line[1]);
                    Rating.add(new Rating(a1,number[0],number[1],number[2]));
                }
            }


            // Объединяем старые и новые данные о рейтинге (если только что сыгравший игрок уже был в рейтинге)
            for (int i=0;i<Rating.size();i++){
                for (int g=i;g<Rating.size();g++){
                    if ((Rating.get(i).getNickName().equals( Rating.get(g).getNickName() ))&(i!=g)){
                        Rating.get(i).setWin( Rating.get(i).getWin()+Rating.get(g).getWin() );
                        Rating.get(i).setLose( Rating.get(i).getLose()+Rating.get(g).getLose() );
                        Rating.get(i).setDeadHeat( Rating.get(i).getDeadHeat()+Rating.get(g).getDeadHeat() );
                        Rating.remove(g);
                    }
                }
            }


            //Сортировка игроков (производится в порядке убывания кол-ва побед)
            Rating.sort(new Comparator<Rating>() {
                public int compare(Rating o1, Rating o2) {
                    if (o1.getWin() == o2.getWin()) return 0;
                    else if (o1.getWin()< o2.getWin()) return 1;
                    else return -1;
                }
            });

            BufferedWriter resultWrite1 = new BufferedWriter(new FileWriter("Result.txt", StandardCharsets.UTF_8));

            // Записываем обновленный рейтинг в файл Result.txt
            resultWrite1.write("Побед - поражений - ничьих\r\n\r\n");
            for (int i = 0; i < Rating.size(); i++) {
                resultWrite1.write("Игрок: "+Rating.get(i).getNickName() + "\r\n");
                resultWrite1.write(Rating.get(i).getWin()+"-"+Rating.get(i).getLose()+"-"+Rating.get(i).getDeadHeat()+"\r\n\r\n");
            }

            resultWrite1.flush();
            resultWrite1.close();
            resultRead.close();


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

