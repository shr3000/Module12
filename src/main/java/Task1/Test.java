package Task1;

/*
Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии (запуска
программы).

Другой ее поток выводит каждые 5 секунд сообщение "Прошло 5 секунд". Предусмотрите возможность ежесекундного оповещения
потока, воспроизводящего сообщение, потоком, отсчитывающим время.
 */

import java.util.function.IntConsumer;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountSecond countSecond = new CountSecond();

        Runnable print5 = () -> System.out.println("5 second");
        IntConsumer printNumber = number -> System.out.println(number);

        Thread threadFive = new Thread(() -> {
            try {
                countSecond.number(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadNum = new Thread(() ->{
            try {
                countSecond.fiveSeconds(print5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadNum.start();
        threadFive.start();

    }

}
