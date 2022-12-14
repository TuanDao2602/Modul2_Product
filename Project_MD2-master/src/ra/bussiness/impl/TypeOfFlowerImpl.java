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
            System.out.print("Nh???p v??o t??n lo???i hoa: ");
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
            System.out.println("Nh???p v??o m?? t??? v??? lo???i hoa: ");
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
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C?? s??? d???ng.");
            System.out.println("2. Kh??ng s??? d???ng.");
            System.out.print("l???a ch???n c???a b???n: ");
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
            System.out.print("Nh???p v??o t??n m???i lo???i hoa: ");
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
            System.out.println("Nh???p v??o m?? t??? m???i cho lo???i hoa: ");
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
            System.out.println("Vui l??ng ch???n tr???ng th??i m???i c???a lo???i hoa: ");
            System.out.println("1. C?? s??? d???ng.");
            System.out.println("2. Kh??ng s??? d???ng.");
            System.out.print("l???a ch???n c???a b???n: ");
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
            status = "C??n b??n";
        }else {
            status = "Kh??ng c??n b??n";
        }
        System.out.println("*-------------------------------------------------------------------------------------------------------*");
            System.out.printf(" M?? lo???i hoa: %-15d T??n lo???i hoa: %-30s Tr???ng th??i: %s\n",typeFlower.getTypeFlowerId(),typeFlower.getTypeFlowerName(),status);
            System.out.printf(" M?? t???: %s\n",typeFlower.getContent());
        System.out.println("*-------------------------------------------------------------------------------------------------------*");
    }

    @Override
    public boolean delete(Integer integer) {
        List<TypeOfFlower> list = readFromFile();
        for (TypeOfFlower tyFlower :list) {
            if (tyFlower.getTypeFlowerId() == integer) {
                tyFlower.setTypeFlowerStatus(false);

            }
        }                return writeFromFile( list);

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
