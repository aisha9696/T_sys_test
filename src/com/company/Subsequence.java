package com.company;

/**
 * Created by Aisha on 02.09.2018.
 */

import java.util.*;

public class Subsequence {
        public <Е> boolean find(List <Е> xList, List <Е> yList) { // список произвольных элементов с помошью
            boolean isAllExist = true; //создаем переменную для проверки наличия элементов
            int temp = 0; // нужно чтобы считывать без изменения порядка
            if(xList.isEmpty() || yList.isEmpty()){//проверяем не пусто ли в списках
                isAllExist = false;
            }else {
                for(int j =0; j < xList.size(); j++){//цикл на наличие каждого элемента xList в списке yList
                    if(isAllExist==true){
                        isAllExist = false;//если не существует x в xList-е то
                        for(int i = temp; i<yList.size(); i++) {//цикл на наличие каждого элемента xList в списке yList без изменения порядка
                            if (yList.get(i).equals(xList.get(j))) {// равен ли каждый элемент xList к каждому  элементу yList
                                temp=i; // чтобы с след. цикла проверка начиналось с этого элемента 
                                isAllExist = true;
                                break;
                            }
                        }
                    }else break;
                }
            }

            return isAllExist;
        }
}
