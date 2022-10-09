import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    private static int singleThreadSum;
    private static int parallelThreadSum;


    public static void calcSpeedTest(int arraySize) {
        int[] array = ArrayGenerator.fill(arraySize);
        long startSingleThreadTime = System.currentTimeMillis();
        singleThreadSum = SumArray.sum(array);
        long singleThreadTime = System.currentTimeMillis() - startSingleThreadTime;
        long startParallelThreadTime = System.currentTimeMillis();
        parallelThreadSum = ParallelSumArray.sumAsync(array);
        long parallelThreadTime = System.currentTimeMillis() - startParallelThreadTime;
        printReport(arraySize, singleThreadTime, parallelThreadTime);
    }

    public static void printReport(int arraySize, long singleTime, long parallelTime) {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream("calculator_array/src/main/java/report/result.txt", true))) {
            String fastestSum = singleTime < parallelTime ? " однопоточный " : " многопоточный ";
            os.write(String.format("=====Тест массива из %d элементов===== %n", arraySize).getBytes(StandardCharsets.UTF_8));
            os.write(String.format("Время выполнения однопоточного подсчета: %d мс %n", singleTime).getBytes(StandardCharsets.UTF_8));
            os.write(String.format("Время выполнения многопоточного подсчета: %d мс %n", parallelTime).getBytes(StandardCharsets.UTF_8));
            os.write(String.format("Полученная сумма в однопоточном подсчете: %d %n", singleThreadSum).getBytes(StandardCharsets.UTF_8));
            os.write(String.format("Полученная сумма в многопоточном подсчете: %d %n", parallelThreadSum).getBytes(StandardCharsets.UTF_8));
            os.write(String.format("При подсчете суммы элементов массива длиной %d элементов быстрее использовать %s подсчет %n", arraySize, fastestSum)
                    .getBytes(StandardCharsets.UTF_8));
            os.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] arraysSizes = {100_000, 1_000_000, 10_000_000};
        for (int size : arraysSizes) {
            calcSpeedTest(size);
        }
    }
}