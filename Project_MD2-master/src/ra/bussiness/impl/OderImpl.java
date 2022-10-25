package ra.bussiness.impl;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.design.IMember;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.Oder;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;

import java.util.List;
import java.util.Scanner;

public class OderImpl implements IMember<Oder> {
    public static List<Oder> readFromFile (){
        FileAll<Oder> fileAll = new FileAll<>();
        List<Oder> list = fileAll.readFromFile(ShopConstant.ODER_URL);
        return list;
    }
    public static boolean writeFromFile (List<Oder> list){
        FileAll<Oder> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,ShopConstant.ODER_URL);
        return returnData;
    }

    @Override
    public Oder inputData(Scanner scanner) {

        return null;
    }

    @Override
    public void displayData(Oder oder) {

    }
}
