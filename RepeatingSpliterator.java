import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class RepeatingSpliterator<T> implements Spliterator<T>{

    private Spliterator<T> spliterator;
    private int repeat;

    public RepeatingSpliterator(Spliterator<T> spliterator,int repeat){
        this.spliterator=spliterator;
        this.repeat = repeat;
    }

    @Override
    public int characteristics() {
        return this.spliterator.characteristics();
    }

    //we do not know the size of the stream. the collection of the objects we serve.
    @Override
    public long estimateSize() {
        return this.spliterator.estimateSize();
    }

    //like the next() method in the iterator.  returns false if there's no more objects
    //returns true if theres more objects and the iplementation needs to call this implementation many times more.
    // tryAdvance is called by ReferencePiplineObject 
    //consumer is given by ReferencePipelineObject
    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        boolean hasMore  = this.spliterator.tryAdvance(t->{
            IntStream.range(0,repeat).forEach(index -> action.accept(t));
        });
        return hasMore;
    }

    //if we want to process stuff in parallel.
    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

}
