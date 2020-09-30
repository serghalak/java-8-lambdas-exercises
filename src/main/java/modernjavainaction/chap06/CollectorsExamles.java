package modernjavainaction.chap06;

import modernjavainaction.chap04.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsExamles {

    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        //counting(menu);
        //maxCalories(menu);
        totalCalories(menu);
        //group(menu);
        //groupEnum(menu);
        //groupFilter(menu);
        //groupMapping(menu);
        //groupflatMapping(menu);
    }

    private static void counting(List<Dish> menu){
        Long collect = menu.stream().collect(Collectors.counting());
        System.out.println("total count of dishes: " + collect);
    }

    private static void maxCalories(List<Dish>menu){
        Optional<Dish> collect = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(collect.get());
    }

    private static void totalCalories(List<Dish>menu ){
        Integer collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("Total: " + collect);
    }

    private static void totalStatisticCalories(List<Dish>menu ){
        Integer collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("Total: " + collect);
    }

    private static void group(List<Dish> menu){
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
    }

    public enum CaloricLevel{DIET,NORMAL,FAT}
    private static void groupEnum(List<Dish> menu){
        Map<CaloricLevel, List<Dish>> caloricDishesByType = menu.stream().collect(Collectors.groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 400 && dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }
        ));
        System.out.println(caloricDishesByType);
    }

    private static void groupFilter(List<Dish> menu){

        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(
                Collectors.groupingBy(
                        Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 500,
                                Collectors.toList())
                ));
        System.out.println(collect);
    }

    private static void groupMapping(List<Dish> menu){

        Map<Dish.Type, List<String>> collect = menu.stream().collect(
                Collectors.groupingBy(
                        Dish::getType
                        ,Collectors.mapping(
                                Dish::getName
                                ,Collectors.toList()
                        )
                )
        );
        System.out.println(collect);
    }

    private static void groupflatMapping(List<Dish> menu){
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("french fries", Arrays.asList("greasy", "fried"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> collect = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType
                        , Collectors.flatMapping(
                                dish -> dishTags.get(dish.getName()).stream()
                                , Collectors.toSet())));

        System.out.println(collect);
    }
}
