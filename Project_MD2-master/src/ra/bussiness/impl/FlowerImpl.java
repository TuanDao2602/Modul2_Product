package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.IFlower;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.Flower;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerImpl implements IShop<Flower,String> , IFlower {
    public static List<Flower> readFromFile (){
        FileAll<Flower> fileAll = new FileAll<>();
        List<Flower> flowerList =  fileAll.readFromFile(DataUrl.FLOWER_URL);
        return flowerList;
    }
    public static boolean writeFromFile (List<Flower> list){
        FileAll<Flower> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,DataUrl.FLOWER_URL);
        return returnData;
    }
    @Override
    public boolean create(Flower flower) {
        List<Flower> flowerList = readFromFile();
        if (flowerList==null){
            flowerList = new ArrayList<>();
        }
        flowerList.add(flower);
        boolean result = writeFromFile(flowerList);
        return result;
    }

    @Override
    public boolean update(Flower flower) {
        List<Flower> listFlower = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listFlower.size(); i++) {
            if (listFlower.get(i).getFlowerId().equals(flower.getFlowerId())) {
                //Thuc hien cap nhat
                listFlower.set(i, flower);
                returnData = true;
                break;
            }
        }
        boolean result = writeFromFile(listFlower);
        if (result && returnData) {
            return true;
        }
        return false;
    }




    @Override
    public List<Flower> findAll() {
        return readFromFile();
    }

    @Override
    public Flower inputData(Scanner scanner) {
        List<Flower> listFlower = readFromFile();
        if (listFlower==null){
            listFlower = new ArrayList<>();
        }
        Flower flower = new Flower();
        do {
            System.out.print("Nh???p v??o m?? lo??i hoa: ");
            String id = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkId5(id);
            if (check){
                check = shopValidate.checkFlowerId(id);
                if (check){
                    for (Flower flowers :listFlower) {
                        if (flowers.getFlowerId().equals(id)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        flower.setFlowerId(id);
                        break;
                    }else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                }else {
                    System.err.println("M?? lo??i hoa b???t ?????u b???ng k?? t??? 'H' !!!");
                }
            }else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o t??n lo??i hoa: ");
            String name = scanner.nextLine();
            boolean check = shopValidate.checkValidateName(name);
            if (check){
                for (Flower flowers :listFlower) {
                    if (flowers.getFlowerName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    flower.setFlowerName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n lo???i hoa !");
            List<TypeOfFlower> listTypeFlower = TypeOfFlowerImpl.readFromFile();
            System.out.printf("%-10s%-30s\n","STT"," T??n lo???i hoa ");
            for (int i = 0; i < listTypeFlower.size(); i++) {
                System.out.printf("%-10d%-30s\n",(i+1),listTypeFlower.get(i).getTypeFlowerName());
            }
            System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
            int choice = 0 ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice <0 || choice>listTypeFlower.size()){
                    System.err.println("Vui l??ng nh???p t??? 1 - " + listTypeFlower.size());
                }else {
                    flower.setTypeOfFlower(listTypeFlower.get(choice-1));
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o gi?? ti???n nh???p v??o: ");
            String price = scanner.nextLine();
            boolean check = shopValidate.checkInputPrice(price);
            if (check){
                flower.setImportPrince(Float.parseFloat(price));
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o gi?? b??n: ");
            String price = scanner.nextLine();
            boolean check = shopValidate.checkInputPrice(price);
            if (check){
                if (Float.parseFloat(price)>flower.getImportPrince()){
                    flower.setExportPrice(Float.parseFloat(price));
                    break;
                }else {
                    System.err.println(ShopMessage.EXPORT_PRICE_MORE_THAN_IMPORT_PRINCE);
                }

            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? lo??i hoa:  ");
            String content = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                flower.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n hoa.");
            System.out.println("2. H???t hoa.");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = scanner.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                flower.setFlowerStatus(true);
                break;
            }else if (check.equals("2")){
                flower.setFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return flower;
    }

    @Override
    public void displayData(Flower flower) {
        String status  = "";
        if (flower.isFlowerStatus()){
            status = "C??n hoa";
        }else {
            status = "h???t hoa";
        }
        System.out.println("*---------------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf( " M?? lo???i hoa: %-15s  T??n lo???i hoa: %-15s  G??a B??n :%-15f  Tr???ng th??i: %-15s        \n",flower.getFlowerId(),flower.getFlowerName(),flower.getExportPrice(),status);
        System.out.printf( " M?? t???: %s                                                                    \n",flower.getContent());
        System.out.println("*---------------------------------------------------------------------------------------------------------------------------------*");
//        System.out.printf("%-15s%-30s%-15f%-15s\n","M?? lo???i hoa","T??n lo???i hoa","G??a B??n","Tr???ng th??i");
//        System.out.printf("%-15s%-30s%-15f%-15s\n",flower.getFlowerId(),flower.getFlowerName(),flower.getExportPrice(),status);
//        System.out.printf("%s","M?? t???:");
//        System.out.printf("%s",flower.getContent());



    }

    @Override
    public boolean delete(String str) {
        List<Flower> list = readFromFile();
        for (Flower flower :list) {
            if (flower.getFlowerId().equals(str)){
                flower.setFlowerStatus(false);

            }
        }
        return writeFromFile(list);
    }


    public static void inputFlower (Scanner scanner){
        FlowerImpl flowerImpl = new FlowerImpl();
        System.out.println("Nh???p v??o s??? l?????ng lo??i hoa b???n mu???n nh???p l???n n??y: ");
        int number = 0;
        while (true){
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }

        for (int i = 0; i < number; i++) {
            System.out.println("Nh???p v??o lo??i hoa th??? " + (i+1));
            Flower flower = new Flower();
            flower = flowerImpl.inputData(scanner);
            boolean check = flowerImpl.create(flower);
            if (check){
                System.out.println("???? th??m m???i th??nh c??ng !");
            }else {
                System.err.println("Th??m m???i th???t b???i !!!");
            }
        }
    }
    public static void displayFlower (){
        FlowerImpl flowerImpl = new FlowerImpl();
        List<Flower> list = readFromFile();
        for (Flower flower: list) {
            flowerImpl.displayData(flower);
        }
    }
    public static void deleteFlower (Scanner scanner){
        FlowerImpl flowerImpl = new FlowerImpl();
        List<Flower> list = readFromFile();
       while (true){
           System.out.println("Nh???p v??o m?? lo??i hoa c???n s???a : ");
           String id = scanner.nextLine();
           boolean check = shopValidate.checkEmptyString(id);
           if (check){
               check = flowerImpl.delete(id);
               if (check){
                   System.out.println("X??a th??nh c??ng ! ");
                   break;
               }else {
                   System.err.println("X??a th???t b???i !!!");
               }
           }else {
               System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
           }
       }
    }
    public Flower newInputData(Flower flowerNew,Scanner sc) {
        List<Flower> listFlower = readFromFile();
        do {
            System.out.println("Nh???p v??o t??n lo??i hoa: ");
            String name = sc.nextLine();
            boolean check = shopValidate.checkValidateName(name);
            if (check){
                for (Flower flowers :listFlower) {
                    if (flowers.getFlowerName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    flowerNew.setFlowerName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n lo???i hoa !");
            TypeOfFlowerImpl flowerTypesImp = new TypeOfFlowerImpl();
            List<TypeOfFlower> listTypeFlower = flowerTypesImp.readFromFile() ;
            System.out.printf("%-10s%-30s\n","STT"," T??n lo???i hoa ");
            for (int i = 0; i < listTypeFlower.size(); i++) {
                System.out.printf("%-10d%-30s\n",(i+1),listTypeFlower.get(i).getTypeFlowerName());
            }
            System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
            int choice = 0 ;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice <0 || choice>listTypeFlower.size()){
                    System.err.println("Vui l??ng nh???p t??? 1 - " + listTypeFlower.size());
                }else {
                    flowerNew.setTypeOfFlower(listTypeFlower.get(choice-1));
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);

        do {
            System.out.println("Nh???p v??o gi?? ti???n nh???p v??o: ");
            String price = sc.nextLine();
            boolean check = shopValidate.checkInputPrice(price);
            if (check){
                flowerNew.setImportPrince(Float.parseFloat(price));
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o gi?? b??n: ");
            String price = sc.nextLine();
            boolean check = shopValidate.checkInputPrice(price);
            if (check){
                if (Float.parseFloat(price)>flowerNew.getImportPrince()){
                    flowerNew.setExportPrice(Float.parseFloat(price));
                    break;
                }else {
                    System.err.println(ShopMessage.EXPORT_PRICE_MORE_THAN_IMPORT_PRINCE);
                }

            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? lo??i hoa:  ");
            String content = sc.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                flowerNew.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n hoa.");
            System.out.println("2. H???t hoa.");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = sc.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                flowerNew.setFlowerStatus(true);
                break;
            }else if (check.equals("2")){
                flowerNew.setFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return flowerNew;
    }

    @Override
    public boolean searchByName(String str) {
            List<Flower> list = readFromFile();
            for (Flower flower :list) {
                if (flower.getFlowerId().contains(str)){
                    displayData(flower);
                    return true;
                }
            }
            return false;
    }

    @Override
    public boolean searchByPrice(float price) {
            List<Flower> list = readFromFile();
            for (Flower flower :list) {
                if (flower.getExportPrice()==price){
                    displayData(flower);
                    return true;
                }
            }
            return false;
        }
    }

