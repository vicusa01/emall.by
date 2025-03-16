package by.emall.ui.utils;

public class WaitUtil {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания потока
            e.printStackTrace();
        }
    }
}