package modernjavainaction.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

/**
 * Created by serg on 01.07.20.
 */
public class TestStrem {
    public static void main(String[] args) {

        //menu.sort((d1,d2) -> d1.getCalories()-d2.getCalories());
        //takeWhile(menu);
        //limits(menu);
        //firstTwoMeatDishes(menu);
        //flatMap(Arrays.asList("Hello","World"));
        //comb(Arrays.asList(1,2,3), Arrays.asList(3,4));
        //optional(menu);
        reduce(menu);
    }

    private static void takeWhile(List<Dish> menu){
//        System.out.println(menu);
//        menu.stream()
//                .filter(dish -> dish.getCalories()<351)
//                .forEach(System.out::println);
        List<Dish> collect =
                menu.stream()
                        .takeWhile(dish -> dish.getCalories()<351)
                        .collect(toList());
        List<Dish> collect1 = menu.stream()
                .dropWhile(dish -> dish.getCalories() < 351)
                .collect(toList());
        System.out.println(collect);
        System.out.println(collect1);

    }

    private static void limits(List<Dish>menu){
        menu.stream()
                .filter(dish -> dish.getCalories()>300)
                .skip(5)
                .limit(3)
                .forEach(System.out::println  );
    }

    private static void firstTwoMeatDishes(List<Dish>menu){
        menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .limit(2).forEach(System.out::println);
    }

    private static void flatMap(List<String>str){
        List<String> collect = str.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(collect);

    }



    private static void comb(List<Integer> numbers1,List<Integer>numbers2){

        List<int[]> collect = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(ints -> ((ints[0]+ints[1])%3==0))
                .collect(toList());

        for (int[] i : collect) {
            System.out.println(Arrays.toString(i));
        }

    }

    private static void optional(List<Dish>menu){
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));
    }

    private static void reduce(List<Dish>menu){

        Integer reduce = menu.stream()
                .map(dish -> 1)
                .reduce(0, Integer::sum);

        //long count = menu.stream().count();
        System.out.println("Total count dishes is: " + reduce);


    }
}
