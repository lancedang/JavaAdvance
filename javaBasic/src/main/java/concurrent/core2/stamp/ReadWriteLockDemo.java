package concurrent.core2.stamp;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Predicate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        ReadWriteLockRoom manager = new ReadWriteLockRoom();

        List<Character> choices = Lists.newArrayList('a', 'b', 'c', 'x');

        int threads = 10;

        int taskNumber = 5;

        ExecutorService executorService = Executors.newCachedThreadPool();

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean evaluate(String object) {
                return object.startsWith("x");
            }

        };

        Runnable addTask = new Runnable() {
            @Override
            public void run() {
//                for (int i = 0; i < 10; i++) {
                    Random random = new Random();
                    int index = random.nextInt(4);
                    Character character = choices.get(index);
                    manager.add(character.toString());

//                }
            }
        };

        new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    log.info("");
                }
            }
        };

        Runnable searchTask = new Runnable() {
            @Override
            public void run() {
//                for (int i = 0; i < 10; i++) {
                List<String> search = null;
                try {
                    search = manager.search(predicate);
                } catch (Exception e) {
                    log.error("search error, ", e);
                }
                log.info("search result={}", search);
//                }
            }
        };


        for (int i = 0; i < taskNumber; i++) {
            executorService.submit(addTask);
        }


        for (int i = 0; i < taskNumber; i++) {
            executorService.submit(searchTask);
        }

    }
}

