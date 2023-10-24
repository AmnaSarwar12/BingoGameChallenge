import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;
        for(char c: "BINGO".toCharArray()){
            for(int i = start; i < (start + 15); i++){
                bingoPool.add("" + c + i);
                //System.out.println("" + c + i);
            }
            start += 15;
        }
        Collections.shuffle(bingoPool);
        for(int i = 0; i < 15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------");

        //List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf('O') == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n---------------------------------");

        for(int i = 0; i < 15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------");

        bingoPool.stream()
                .limit(15)
                .filter(s-> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s-> s.charAt(0) + " - " + s.substring(1))
                .sorted()
                .forEach(s-> System.out.println(s + " "));

        for(int i = 0; i < 15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------");

        String[] strings = {"one","two","three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
                //.forEach(System.out::println);

        var secondStream = Stream.of("four","five","six")
                .map(String::toUpperCase);
                //.forEach(System.out::println);

        Stream.concat(secondStream, firstStream)
                .map(s->s.charAt(0) + "-" + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for(char c : "BINGO".toCharArray()){
            int[] number = new int[15];
            int labelno = bingoIndex;
            Arrays.setAll(number, i -> i + labelno);
            myMap.put(c, number);
            bingoIndex += 15;
        }

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                        e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

        Random random =  new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s->System.out.print(s + " "));

        System.out.println();
        IntStream .iterate(1, n -> n+1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.rangeClosed(1, 100)
                .filter(Main::isPrime)
                .forEach(s-> System.out.print(s + " "));

    }

    public static boolean isPrime(int wholeNumber){
        if(wholeNumber <= 2 ){
            return wholeNumber == 2;
        }
        for(int divisor = 2; divisor < wholeNumber; divisor++){
            if(wholeNumber % divisor == 0){
                return false;
            }
        }
        return true;
    }
}