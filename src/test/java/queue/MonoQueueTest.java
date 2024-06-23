package queue;

import com.fork52.queue.MonoQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MonoQueueTest {

    private <T> List<T> getSlidingWindowBruteForce(
        List<T> arr, int windowSize, Comparator<T> comparator) {

        return IntStream.range(0, arr.size())
            .mapToObj(i -> new int[]{Math.max(i - windowSize + 1, 0), i + 1})
            .map(range -> arr.subList(range[0], range[1])
                .stream()
                .min(comparator)
                .get()
            ).toList();
    }

    private <T> List<T> getSlidingWindowMonoQueue(
        List<T> arr, int windowSize, Comparator<T> comparator) {
        MonoQueue<T> mq = new MonoQueue<>(arr, comparator);
        List<T> result = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            mq.add(i);
            mq.removeElementsBefore(i - windowSize);
            result.add(mq.getBest());
        }

        return result;
    }

    @Test
    void testMinMonoQueue() {
        List<Integer> arr = List.of(2, 3, 30, 100, 50, 23, 15);
        int windowSize = 3;

        List<Integer> expected = getSlidingWindowBruteForce(arr, windowSize, Integer::compare);
        List<Integer> actual = getSlidingWindowMonoQueue(arr, windowSize, Integer::compare);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMaxMonoQueue() {
        List<Integer> arr = List.of(2, 3, 30, 100, 50, 23, 15, 60,
            20, 320, 6, -5, 500, 20, 40);
        int windowSize = 5;

        List<Integer> expected = getSlidingWindowBruteForce(arr, windowSize, Collections.reverseOrder());
        List<Integer> actual = getSlidingWindowMonoQueue(arr, windowSize, Collections.reverseOrder());

        Assertions.assertEquals(expected, actual);
    }


}
