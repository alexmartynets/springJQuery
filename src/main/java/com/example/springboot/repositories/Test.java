package com.example.springboot.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0, 2);
        list.add(1, 1);
        list.add(2, 7);
        System.out.println(getTotalTime(list, 2));

    }

    public static int getTotalTime(List<Integer> heroes, int n) {
        int sum = 0;
        int[] heroesarr = heroes.stream().mapToInt(x -> x).toArray();
        if (n == 1) {
            return heroes.stream().flatMapToInt(IntStream::of).sum();
        } else {
            System.out.println(Arrays.toString(heroesarr));
            for (int i = 0; i < heroesarr.length; i++){
                if (heroesarr[i] > heroesarr[1]){
                    sum = heroesarr[0] + heroesarr[2];
                } else {
                    sum = heroesarr[1]+heroesarr[3];
                }
            }
        }
        return sum;
    }
}
//    С целью выведения самой жизнеспособной расы ученые умы решили провести селекцию на основе проверенного временем древнего теста ДНК
//    (Драки с Непобедимыми Кентаврами).
//        Для этого сильнейшие представители каждой расы heroes в порядке очереди вступают в бой с одним из n древних Кентавров.
//        Каждый герой может продержаться в бою heroes[i] минут.
//        Количество кентавров не уменьшается (не зря их прозвали Непобедимыми). Определите время, за которое все бои будут завершены.
//
//        На входе:
//
//        heroes - массив целых неотрицательных чисел. Каждое число обозначает время поединка героя в очереди  (всегда больше 0)
//        n - количество Кентавров (всегда больше 0)
//        На выходе: число (int) - время, за которое завершатся все поединки
//
//        Пример:
//        1. если кентавр один, то общее время равно сумме времени каждого участника
//
//        total_time([6, 2, 9], 1) --> 17
//        2.  кентавров два, поэтому первый и второй герои вступают в бой одновременно, затем, после второго, третий и четвертый сражаютя по очереди со вторым кентавром
//
//        total_time([11, 2, 3, 4], 2) --> 11
//        3. кентавров два, первый и второй герои одновременно вступают в бой, после первого героя третий будет биться с тем же кентавром
//
//        total_time([3, 5, 10], 2) --> 13