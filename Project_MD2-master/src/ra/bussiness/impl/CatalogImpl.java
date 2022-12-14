package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.ICatalog;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.Catalog;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogImpl implements IShop<Catalog,String> , ICatalog {
    public static List<Catalog> readFromFile (){
        FileAll <Catalog> fileAll = new FileAll<>();
        List<Catalog> list = fileAll.readFromFile(DataUrl.CATALOG_URL);
        return list;
    }
    public static boolean writeFromFile (List<Catalog> list){
        FileAll<Catalog> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,DataUrl.CATALOG_URL);
        return returnData;
    }

    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> catalogList = readFromFile();
        if (catalogList==null){
            catalogList = new ArrayList<>();
        }
        catalogList.add(catalog);
        boolean result = writeFromFile(catalogList);
        return result;
    }

    @Override
    public boolean update(Catalog catalog) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listCatalog.size(); i++) {
            if (listCatalog.get(i).getCatalogId().equals(catalog.getCatalogId()) ) {
                //Thuc hien cap nhat
                listCatalog.set(i, catalog);
                returnData = true;
                break;
            }
        }
        boolean result = writeFromFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;
    }

    @Override
    public List<Catalog> findAll() {
        return readFromFile();
    }

    @Override
    public Catalog inputData(Scanner scanner) {
        List <Catalog> listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        Catalog catalog = new Catalog();
        do {
            System.out.println("Nh???p v??o m?? danh m???c s???n ph???m: ");
            String id = scanner.nextLine();
            boolean check = shopValidate.checkId5(id);
            if (check){
                check = shopValidate.checkCatalogId(id);
                if (check){
                    for (Catalog cat :listCatalog) {
                        if (cat.getCatalogId().equals(id)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        catalog.setCatalogId(id);
                        break;
                    }else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                }else {
                    System.err.println(ShopMessage.CATALOG_ID_WRONG);
                }
            }else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o t??n danh m???c: ");
            String name = scanner.nextLine();
            boolean check = shopValidate.checkValidateName(name);
            if (check){
                for (Catalog cat :listCatalog) {
                    if (cat.getCatalogName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    catalog.setCatalogName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? danh m???c: ");
            String content = scanner.nextLine();
            boolean check= shopValidate.checkEmptyString(content);
            if (check){
                catalog.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. Ho???t ?????ng .");
            System.out.println("2. Kh??ng ho???t ?????ng.");
            System.out.print("l???a ch???n c???a b???n: ");
            String choice = scanner.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                catalog.setCatalogStatus(true);
                break;
            }else if (check.equals("2")){
                catalog.setCatalogStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        do {
            try {
                System.out.println("Nh???p v??o ????? ??u ti??n: ");
                int priority = Integer.parseInt(scanner.nextLine());
                catalog.setPriority(priority);
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }

        }while (true);

        return catalog;
    }


    public  Catalog inputDataNew(Catalog catalogNew,Scanner sc){
        List<Catalog> catalogList = readFromFile();
        // Kh???i t???o ?????i t?????ng ????? nh???n th??ng tin
        String newName ="";
        String newTitle="";
        int newprioty;
        do {
            System.out.println("Nh???p v??o t??n m???i cho danh m???c: ");
            newName= sc.nextLine();
            boolean check = shopValidate.checkValidateName(newName);
            if (check){
                for (Catalog catalog1 :catalogList) {
                    if (catalog1.getCatalogName().equals(newName)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    catalogNew.setCatalogName(newName);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? m???i cho danh m???c: ");
            newTitle = sc.nextLine();
            boolean check= shopValidate.checkEmptyString(newTitle);
            if (check){
                catalogNew.setContent(newTitle);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            try {
                System.out.println("Nh???p v??o ????? ??u ti??n m???i cho danh m???c: ");
                newprioty = Integer.parseInt(sc.nextLine());
                catalogNew.setPriority(newprioty);
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }

        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. Ho???t ?????ng .");
            System.out.println("2. Kh??ng ho???t ?????ng.");
            System.out.print("l???a ch???n c???a b???n: ");
            String choice = sc.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                catalogNew.setCatalogStatus(true);
                break;
            }else if (check.equals("2")){
                catalogNew.setCatalogStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);


        return catalogNew;
    }



    @Override
    public void displayData(Catalog catalog) {
        String status = "";
        if (catalog.isCatalogStatus()){
            status = " Ho???t ?????ng ";
        }else {
            status = "Kh??ng ho???t ?????ng";
        }
        System.out.println("*-------------------------------------------------------------------------------------------------------*");
        System.out.printf(" M?? danh m???c: %-15s  T??n Danh M???c: %-30s  Tr???ng th??i: %-15s\n",catalog.getCatalogId(),catalog.getCatalogName(),status);
        System.out.printf(" bM?? t???: %s\n",catalog.getContent());
        System.out.println("*-------------------------------------------------------------------------------------------------------*");

    }

    @Override
    public boolean delete(String id) {
        List<Catalog> list = readFromFile();
        for (Catalog cat :list) {
            if (cat.getCatalogId().equals(id)){
                cat.setCatalogStatus(false);


            }
        }
        return writeFromFile(list);
    }

    @Override
    public void searchByName(String name) {
        List<Catalog> list = readFromFile();
        for (Catalog catalog:list) {
            if (catalog.getCatalogName().contains(name)){
                displayData(catalog);
            }
        }

    }
    public static void inputCatalog (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        System.out.println("Nh???p v??o s??? danh m???c s???n ph???m b???n mu???n nh???p l???n n??y: ");
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
            Catalog catalog = new Catalog();
            catalog = catalogImpl.inputData(scanner);
            boolean check = catalogImpl.create(catalog);
            if (check){
                System.out.println("???? th??m m???i th??nh c??ng !");
            }else {
                System.err.println("Th??m m???i th???t b???i !!!");
            }
        }
    }
    public static void displayCatalog (){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        for (Catalog catalog: list) {
            catalogImpl.displayData(catalog);
        }
    }
    public static void deleteCatalog (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        while (true){
            System.out.println("Nh???p v??o m?? lo??i hoa c???n s???a : ");
            String id = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(id);
            if (check){
                check = catalogImpl.delete(id);
                if (check){
                    System.out.println("X??a th??nh c??ng ! ");
                    break;
                }else {
                    System.err.println("Kh??ng t??m th???y m?? n??y vui l??ng nh???p v??o m?? kh??c !!!");
                }
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }
    }
    public static void searchCatalogByName (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        System.out.println("Nh???p v??o t??n danh m???c s???n ph???m b???n mu???n t??m ki???m : ");
        String name = scanner.nextLine();
         catalogImpl.searchByName(name);
    }
}
