import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams{
    public static void main(String [] args) {
        List<Integer> strings = Arrays.asList(1,2,3,4,5,6,7,8,9,10);


        // strings.stream().map(i -> i*2).filter(i -> i%2!=0).forEach(System.out::println);

        // stream works from spliterator and ReferencePipelineHead
        Stream<Integer> stream1 = strings.stream();
        stream1.onClose(()-> System.out.println("Closing the stream"));//on closing the stream
        Spliterator<Integer> iterator  = stream1.sorted().spliterator();

        // Stream<Integer> stream = StreamSupport.stream(iterator, false);

        // stream.forEach(System.out::println);

        //basically the same thing

        NoOpSpliterator<Integer> NoOpSpliterator  = new NoOpSpliterator<>(iterator);

        //stream().filter()
        FilteringSpliterator<Integer> filteringSpliterator = new FilteringSpliterator<>(NoOpSpliterator, s-> s % 2==0);

        //stream().map()
        MappingSpliterator<Integer,String> mappingSpliterator = new MappingSpliterator<>(NoOpSpliterator,obj -> obj.toString() + "tb");

        RepeatingSpliterator<Integer> repeatingSpliterator = new RepeatingSpliterator<>(NoOpSpliterator, 3);

        Stream<Integer> stream = StreamSupport.stream(repeatingSpliterator, false);
        stream.onClose((stream1::close));

        stream.forEach(System.out::println);
        stream.close();








    }
}