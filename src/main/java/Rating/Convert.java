package Rating;

public class Convert {

    // Вспомогательный метод для перевода рейтинга из файла Result.txt
    // (они читаются из файла как String) в массив Rating
    public static int[] Convert (String text){

        String tempText="";
        int check=0;
        // a[0]- кол-во побед, a[1]- кол-во поражений, a[2]- кол-во ничьих
        int[] a = new int[]{0,0,0};
        char[] array = text.toCharArray();

        // Посимвольно проходим строку формата int-int-int (победы-поражения-ничьи) и переводим ее в массив int
        for (int i = 0; i < array.length; i++)
        {

            if ((array[i] != '-')&(check==0)) {
                tempText = tempText+array[i];
                a[0]=Integer.parseInt(tempText);
            } else if (check==0){
                tempText="";
                check=1;
                continue;
            }

            if ((array[i] != '-')&(check==1)) {
                tempText = tempText+array[i];
                a[1]=Integer.parseInt(tempText);
            } else if (check==1) {
                tempText="";
                check=2;
                continue;
            }

            if ((array[i] != '-')&(check==2)) {
                tempText = tempText+array[i];
                a[2]=Integer.parseInt(tempText);
            } else if (check==2) {
                tempText="";
                check=3;
                continue;
            }
        }

        return a;
    }
}


