package com.company;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aisha on 02.09.2018.
 */
public class PyramidBuilder {
    public void getPyramids (int []items) throws CannotBuildPyramidException{
        //сортируем массив
        Arrays.sort(items);
        int pyramid[][]= new int[0][0];
        // решение задачи выводится из формулы арифметического прогрессии
        // n - число столбцов пирамиды
        // S - число размера массива
        // a1 - равен 1
        // из формулы суммы сумма арифметической прогрессии S =((a1+n)*n)/2
        // создаем уровнение для вычисление дискрименанта n^2 + a1*n - 2*S = 0

        double D = Math.sqrt(1 + 8* items.length);

        if(D>0 && D%1==0){
            int n = (int) ((D-1)/2);
            pyramid = new int[n][n+(n-1)];

            int k=0;
            for(int i = 0; i<n; i++) {
                for (int j = 0; j < n + (n - 1); j++) {
                    pyramid[i][j]=0;
                }
                int b = n-i-1;
                for (int j = 0; j <= i; j++) {
                    pyramid[i][b]=items[k++];
                    b=2+b;
                }

            }

            for(int i = 0; i<n; i++) {
                for (int j = 0; j < n + (n - 1); j++) {
                    System.out.print(pyramid[i][j]);
                }
                System.out.print("\n");
            }

        }else{
           throw new CannotBuildPyramidException("Cant build pyramid");
        }


    }

}
