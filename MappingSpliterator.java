import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class MappingSpliterator<T,R> implements Spliterator<T>{

    private Spliterator<T> spliterator;
    private UnaryOperator<T> mapper;

    public MappingSpliterator(Spliterator<T> spliterator, UnaryOperator<T> mapper){
        this.spliterator=spliterator;
        this.mapper = mapper;
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
            action.accept(mapper.apply(t));
        });
        return hasMore;
    }

    //if we want to process stuff in parallel.
    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

}
