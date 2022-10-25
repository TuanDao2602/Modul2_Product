package ra.bussiness.design;

import java.util.Scanner;

public interface IMember <T> {
    T inputData (Scanner scanner);
    void displayData(T t);
}
