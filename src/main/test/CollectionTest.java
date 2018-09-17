import com.smart.collect.TopNCollection;
import org.junit.Test;

import java.util.Comparator;

public class CollectionTest {

    @Test
    public void test() {
        //顺序排列
        TopNCollection<Integer> ascCollection = new TopNCollection<>(20, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        ascCollection.put(10);
        ascCollection.put(15);
        ascCollection.put(13);
        ascCollection.put(12);
        ascCollection.put(12);
        ascCollection.put(12);

        System.out.println(ascCollection.getTopN());

        //逆序排列
        TopNCollection<Integer> descCollection = new TopNCollection<>(2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * Integer.compare(o1, o2);
            }
        });

        descCollection.put(10);
        descCollection.put(15);
        descCollection.put(13);
        descCollection.put(12);
        descCollection.put(12);
        descCollection.put(12);

        System.out.println(descCollection.getTopN());
    }
}
