package modernjavainaction.chap05;

//import org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {
  public static List<Transaction> getTransaction(){
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
    return transactions;
  }


  public static void main(String... args) {

//    query1(getTransaction());
//    query2(getTransaction());
//    query3(getTransaction());
//    query4(getTransaction());
//    query5(getTransaction());
//    query6(getTransaction());
//    query7(getTransaction());
//    query8(getTransaction());
//    triplex();
//      files();
//      fibonacci();
      iterator();
  }

  private static void query1(List<Transaction> transactions){

    // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
    List<Transaction> tr2011 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted((o1, o2) -> o1.getValue() - o2.getValue())
            .collect(toList());
    System.out.println(tr2011);

  }

  private static void query2(List<Transaction> transactions){
    // Query 2: What are all the unique cities where the traders work?
    List<String> collect = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .collect(toList());

    System.out.println(collect);

  }

  private static void query3(List<Transaction> transactions){
    // Query 3: Find all traders from Cambridge and sort them by name.

    List<Transaction> cambridge = transactions.stream()
            .filter(
                    transaction ->
                            transaction.getTrader().getCity().equals("Cambridge"))
            .sorted((o1, o2) -> o1.getTrader().getName().compareTo(o2.getTrader().getName()))
            .collect(toList());
    System.out.println(cambridge);

  }

  private static void query4(List<Transaction> transactions){
    // Query 4: Return a string of all traders' names sorted alphabetically.

    String reduce = transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .sorted()
            .reduce("", (s, trader) -> String.join(", ",s,trader));
    System.out.println(reduce);
  }


  private static void query5(List<Transaction> transactions){
    // Query 5: Are there any trader based in Milan?

    boolean milan = transactions.stream()
            .anyMatch(
                    transaction ->
                            transaction
                                    .getTrader()
                                    .getCity().equals("Milan"));

    System.out.println(milan);
  }

  // Query 6: Print all transactions' values from the traders living in Cambridge.
  private static void query6(List<Transaction> transactions){

    List<Integer> cambridge = transactions.stream()
            .filter(transaction ->
                    transaction
                            .getTrader()
                            .getCity().equals("Cambridge"))
            .map(transaction -> transaction.getValue())
            .collect(toList());

    System.out.println(cambridge);
  }

  // Query 7: What's the highest value in all the transactions?
  private static void query7(List<Transaction> transactions){

    Optional<Integer> reduce = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);
    System.out.println(reduce.get());
  }

  // Find the transaction with the smallest value
  private static void query8(List<Transaction> transactions){

    Optional<Transaction> min =
            transactions.stream().min((o1, o2) -> (o1.getValue() - o2.getValue()));

    System.out.println(min.get());
  }

  private static void triplex(){

    IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                    .filter(t -> t[2] % 1 == 0))
            .forEach(doubles -> System.out.println(
                    doubles[0] + ":" + doubles[1] + ":" + doubles[2]));



  }

  private static void files(){
      long count=0;
      try(Stream<String> lines = Files.lines(Paths.get("data.txt"))) {
          count = lines.flatMap(s ->   {
            System.out.println("s: " + s);
            return Arrays.stream(s.split(" "));
          }).distinct().count();

          System.out.println("count: " + count);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  private static void fibonacci(){
      Stream.iterate(new int[]{0,1},n->new int[]{n[1],n[0]+n[1]})
              .map(ints -> "("+ints[0]+", " +ints[1]+")")
              .limit(20)
              .forEach(System.out::println);
  }

  private static void iterator(){
    Stream.iterate(0,i ->i<100,n->n+4 )
            .forEach(System.out::println);

  }
}
