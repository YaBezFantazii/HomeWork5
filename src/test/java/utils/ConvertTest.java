package utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertTest {

        String test1 = "1-5-8";
        String test2 = "22-54-92";
        String test3 = "0-0-0";
        String test4 = "44-3422-44314";
        int[] num1 = new int[]{1,5,8};
        int[] num2 = new int[]{22,54,92};
        int[] num3= new int[]{0,0,0};
        int[] num4 = new int[]{44,3422,44314};

    //Тест - правильно ли переводит данные в int[] из String метод java/utils/Convert
    @Test
    public void convert() {

        assertArrayEquals(num1,Convert.Convert(test1));
        assertArrayEquals(num2,Convert.Convert(test2));
        assertArrayEquals(num3,Convert.Convert(test3));
        assertArrayEquals(num4,Convert.Convert(test4));

    }
}