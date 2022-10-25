package ra.bussiness.impl;

import ra.bussiness.design.IMember;
import ra.bussiness.entity.Order;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.List;
import java.util.Scanner;

public class OderImpl implements IMember<Order> {
    public static List<Order> readFromFile (){
        FileAll<Order> fileAll = new FileAll<>();
        List<Order> list = fileAll.readFromFile(DataUrl.ODER_URL);
        return list;
    }
    public static boolean writeFromFile (List<Order> list){
        FileAll<Order> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,DataUrl.ODER_URL);
        return returnData;
    }

    @Override
    public Order inputData(Scanner scanner) {

        return null;
    }

    @Override
    public void displayData(Order oder) {

    }
}
