package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.IShop;
import ra.bussiness.design.ITypeOfFlower;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TypeOfFlowerImpl implements IShop<TypeOfFlower,Integer>, ITypeOfFlower {
    public static List<TypeOfFlower> readFromFile (){
        FileAll<TypeOfFlower> fileAll = new FileAll<>();
        List<TypeOfFlower> list = fileAll.readFromFile(DataUrl.TYPEOFFLOER_URL);
        return list;

    }
    public static boolean writeFromFile (List<TypeOfFlower> list){
        FileAll<TypeOfFlower> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,DataUrl.TYPEOFFLOER_URL);
        return returnData;
    }
    @Override
    public boolean create(TypeOfFlower typeOfFlower) {
        List<TypeOfFlower> list = readFromFile();
        if (list==null){
            list = new ArrayList<>();
        }
        list.add(typeOfFlower);
        boolean result = writeFromFile(list);
        return result;
    }

    @Override
    public boolean update(TypeOfFlower typeOfFlower) {
        List<TypeOfFlower> typeOfFlowers = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < typeOfFlowers.size(); i++) {
            if (typeOfFlowers.get(i).getTypeFlowerId()==(typeOfFlower.getTypeFlowerId())) {
                //Thuc hien cap nhat
                typeOfFlowers.set(i, typeOfFlower);
                returnData = true;
                break;
            }
        }
        boolean result = writeFromFile(typeOfFlowers);
        if (result && returnData) {
            return true;
        }
        return false;

    }




    @Override
    public List<TypeOfFlower> findAll() {
        return readFromFile();
    }

    @Override
    public TypeOfFlower inputData(Scanner scanner) {
        List<TypeOfFlower> list = readFromFile();
        if (list==null){
            list = new ArrayList<>();
        }
        TypeOfFlower toFlower = new TypeOfFlower();
        toFlower.setTypeFlowerId(list.size()+1);
        do {
            System.out.print("Nhập vào tên loại hoa: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkValidateName(name);
            if (check){
                toFlower.setTypeFlowerName(name);
                break;
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào mô tả về loại hoa: ");
            String content = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                toFlower.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn trạng thái của loại hoa: ");
            System.out.println("1. Có sử dụng.");
            System.out.println("2. Không sử dụng.");
            System.out.print("lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (choice.equals("1")){
                toFlower.setTypeFlowerStatus(true);
                break;
            }else if (choice.equals("2")){
                toFlower.setTypeFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return toFlower;
    }
    public TypeOfFlower inputDataNew(TypeOfFlower flowerTypesNew, Scanner sc){

        do {
            System.out.print("Nhập vào tên mới loại hoa: ");
            String name = sc.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkValidateName(name);
            if (check){
                flowerTypesNew.setTypeFlowerName(name);
                break;
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào mô tả mới cho loại hoa: ");
            String content = sc.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                flowerTypesNew.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn trạng thái mới của loại hoa: ");
            System.out.println("1. Có sử dụng.");
            System.out.println("2. Không sử dụng.");
            System.out.print("lựa chọn của bạn: ");
            String choice = sc.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (choice.equals("1")){
                flowerTypesNew.setTypeFlowerStatus(true);
                break;
            }else if (choice.equals("2")){
                flowerTypesNew.setTypeFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return flowerTypesNew;
    }


    @Override
    public void displayData(TypeOfFlower typeFlower ) {
        String status  = "";
        if (typeFlower.isTypeFlowerStatus()){
            status = "Còn bán";
        }else {
            status = "Không còn bán";
        }
        System.out.println("*-------------------------------------------------------------------------------------------------------*");
            System.out.printf(" Mã loại hoa: %-15d Tên loại hoa: %-30s Trạng thái: %s\n",typeFlower.getTypeFlowerId(),typeFlower.getTypeFlowerName(),status);
            System.out.printf(" Mô tả: %s\n",typeFlower.getContent());
        System.out.println("*-------------------------------------------------------------------------------------------------------*");
    }

    @Override
    public boolean delete(Integer integer) {
        List<TypeOfFlower> list = readFromFile();
        for (TypeOfFlower tyFlower :list) {
            if (tyFlower.getTypeFlowerId()==integer){
                tyFlower.setTypeFlowerStatus(false);
                create(tyFlower);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchByNameOrId(String name, String id) {
       List<TypeOfFlower> list = readFromFile();
        for (TypeOfFlower typeF :list) {
            if (typeF.getTypeFlowerName().contains(name) || (typeF.getTypeFlowerId()+"").contains(id)){
                displayData(typeF);
                return true;
            }
        }
        return false;
    }
}
