import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams{
    public static void main(String [] args) {
        List<Integer> strings = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        // strings.stream().forEach(System.out::println);

        // stream works from spliterator and ReferencePipelineHead
        Spliterator<Integer> iterator  = strings.stream().spliterator();

        // Stream<Integer> stream = StreamSupport.stream(iterator, false);

        // stream.forEach(System.out::println);

        //basically the same thing

        NoOpSpliterator<Integer> NoOpSpliterator  = new NoOpSpliterator<>(iterator);
        

        Stream<Integer> stream = StreamSupport.stream(NoOpSpliterator, false);

        stream.forEach(System.out::println);








    }
}