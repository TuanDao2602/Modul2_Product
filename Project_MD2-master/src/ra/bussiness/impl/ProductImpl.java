package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.IProduct;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.*;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductImpl implements IShop<Product, String> , IProduct {
    public static List<Product> readFromFile() {
        FileAll<Product> fileAll = new FileAll<>();
        List<Product> listProduct = fileAll.readFromFile(DataUrl.PRODUCT_URL);
        return listProduct;
    }

    public static boolean writeFromFile(List<Product> list) {
        FileAll<Product> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list, DataUrl.PRODUCT_URL);
        return returnData;
    }

    @Override
    public boolean create(Product product) {
        List<Product> productList = readFromFile();
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
        boolean result = writeFromFile(productList);
        return result;
    }

    @Override
    public boolean update(Product product) {
        List<Product> listProduct = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getProductId().equals(product.getProductId()) ) {
                //Thuc hien cap nhat
                listProduct.set(i, product);
                returnData = true;
                break;
            }
        }
        boolean result = writeFromFile(listProduct);
        if (result && returnData) {
            return true;
        }
        return false;
    }




    @Override
    public List<Product> findAll() {
        return readFromFile();
    }

    @Override
    public Product inputData(Scanner scanner) {
        List<Product> listProduct = readFromFile();
        if (listProduct == null) {
            listProduct = new ArrayList<>();
        }
        Product product = new Product();
        do {
            System.out.print("Nh???p v??o m?? s???n ph???m: ");
            String id = scanner.nextLine();
            boolean check = shopValidate.checkProductId_length(id);
            if (check) {
                check = shopValidate.checkProductId(id);
                if (check) {
                    for (Product prod : listProduct) {
                        if (prod.getProductId().equals(id)) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        product.setProductId(id);
                        break;
                    } else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                } else {
                    System.err.println(ShopMessage.PRODUCT_ID_WRONG);
                }
            } else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        } while (true);
        do {
            System.out.println("Nh???p v??o t??n s???n ph???m: ");
            String name = scanner.nextLine();
            boolean check = shopValidate.checkProductName(name);
            if (check) {
                for (Product pro : listProduct) {
                    if (pro.getProductName().equals(name)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    product.setProductName(name);
                    break;
                } else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            } else {
                System.err.println("T??n s???n ph???m t??? 6 - 50 k?? t??? !!!");
            }
        } while (true);
        int cnt = 0;
        int number = -1;
        do {
            List<Flower> listFlower = FlowerImpl.readFromFile();
            System.out.println("Nh???p v??o s??? l?????ng lo??i hoa cho s???n ph???m n??y: ");

            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui l??ng nh???p v??o s??? !!! ");
            }
            for (int i = 0; i < number; i++) {
                FlowerOfProduct flowerOfProduct = new FlowerOfProduct();
                while (true) {
                    System.out.println("Vui l??ng ch???n lo??i hoa ");
                    System.out.printf("%-10s%-30s\n", "STT", " T??n lo??i hoa ");
                    for (int j = 0; j < listFlower.size(); j++) {
                        System.out.printf("%-10d%-30s\n", (j + 1), listFlower.get(j).getFlowerName());
                    }
                    System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
                    int choice = 0;

                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        if (choice < 0 || choice > listFlower.size()) {
                            System.err.println("Vui l??ng nh???p t??? 1 - " + listFlower.size());
                        } else {
                            flowerOfProduct.setFlower(listFlower.get(choice - 1));
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Vui l??ng nh???p v??o s??? !!!");
                    }
                }
                while (true){
                    try {
                        System.out.println("Nh???p v??o s??? l?????ng ");
                        int choice  = Integer.parseInt(scanner.nextLine());
                        flowerOfProduct.setQuantity(choice);
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Vui l??ng nh???p v??o s??? !!! ");
                    }
                }
                product.getListFlower().add(flowerOfProduct);
                cnt++;
            }
        } while (cnt!= number);
        do {
            float sum = 0;
            for (int i = 0; i < product.getListFlower().size(); i++) {
               sum+= product.getListFlower().get(i).getFlower().getImportPrince()*product.getListFlower().get(i).getQuantity();
            }
            System.out.println("Nh???p v??o gi?? b??n c???a s???n ph???m : ");
            System.out.println("T???ng gi?? nh???p hoa d??ng trong s???n ph???m l??:  " + sum);
            float price = 0;
            try {
                price = Float.parseFloat(scanner.nextLine());
                if (price>sum*1.3f){
                    product.setExportPrice(price);
                    break;
                }else {
                    System.err.println("Vui l??ng nh???p gi?? b??n cao h??n " + (sum*1.3));
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p s??? !!! ");
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? s???n ph???m:  ");
            String content = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                product.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n b??n.");
            System.out.println("2. Kh??ng c??n b??n");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = scanner.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                product.setProductStatus(true);
                break;
            }else if (check.equals("2")){
                product.setProductStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return product;
    }

    public Product inputDataNew(Product product,Scanner scanner) {
        List<Product> listProduct = readFromFile();

        do {
            System.out.println("Nh???p v??o t??n s???n ph???m: ");
            String name = scanner.nextLine();
            boolean check = shopValidate.checkProductName(name);
            if (check) {
                for (Product pro : listProduct) {
                    if (pro.getProductName().equals(name)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    product.setProductName(name);
                    break;
                } else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            } else {
                System.err.println("T??n s???n ph???m t??? 6 - 50 k?? t??? !!!");
            }
        } while (true);
        int cnt = 0;
        int number = -1;
        do {
            List<Flower> listFlower = FlowerImpl.readFromFile();
            System.out.println("Nh???p v??o s??? l?????ng lo??i hoa cho s???n ph???m n??y: ");

            try {
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui l??ng nh???p v??o s??? !!! ");
            }
            for (int i = 0; i < number; i++) {
                FlowerOfProduct flowerOfProduct = new FlowerOfProduct();
                while (true) {
                    System.out.println("Vui l??ng ch???n lo??i hoa ");
                    System.out.printf("%-10s%-30s\n", "STT", " T??n lo??i hoa ");
                    for (int j = 0; j < listFlower.size(); j++) {
                        System.out.printf("%-10d%-30s\n", (j + 1), listFlower.get(j).getFlowerName());
                    }
                    System.out.print("Vui l??ng ch???n s??? t????ng ???ng: ");
                    int choice = 0;

                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                        if (choice < 0 || choice > listFlower.size()) {
                            System.err.println("Vui l??ng nh???p t??? 1 - " + listFlower.size());
                        } else {
                            flowerOfProduct.setFlower(listFlower.get(choice - 1));
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Vui l??ng nh???p v??o s??? !!!");
                    }
                }
                while (true){
                    try {
                        System.out.println("Nh???p v??o s??? l?????ng ");
                        int choice  = Integer.parseInt(scanner.nextLine());
                        flowerOfProduct.setQuantity(choice);
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Vui l??ng nh???p v??o s??? !!! ");
                    }
                }
                product.getListFlower().add(flowerOfProduct);
                cnt++;
            }
        } while (cnt!= number);
        do {
            float sum = 0;
            for (int i = 0; i < product.getListFlower().size(); i++) {
                sum+= product.getListFlower().get(i).getFlower().getImportPrince()*product.getListFlower().get(i).getQuantity();
            }
            System.out.println("Nh???p v??o gi?? b??n c???a s???n ph???m : ");
            System.out.println("T???ng gi?? nh???p hoa d??ng trong s???n ph???m l??:  " + sum);
            float price = 0;
            try {
                price = Float.parseFloat(scanner.nextLine());
                if (price>sum*1.3f){
                    product.setExportPrice(price);
                    break;
                }else {
                    System.err.println("Vui l??ng nh???p gi?? b??n cao h??n " + (sum*1.3));
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p s??? !!! ");
            }
        }while (true);
        do {
            System.out.println("Nh???p v??o m?? t??? s???n ph???m:  ");
            String content = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(content);
            if (check){
                product.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui l??ng ch???n tr???ng th??i c???a lo???i hoa: ");
            System.out.println("1. C??n b??n.");
            System.out.println("2. Kh??ng c??n b??n");
            System.out.print("l???a ch???n c???a b???n: ");
            System.out.print("\n");
            String choice = scanner.nextLine();
            String check = shopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                product.setProductStatus(true);
                break;
            }else if (check.equals("2")){
                product.setProductStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return product;
    }


    @Override
    public void displayData(Product product) {
        String status  = "";
        if (product.isProductStatus()){
            status = "C??n b??n";
        }else {
            status = "Kh??ng c??n b??n. ";

        }
        System.out.println("*-----------------------------------------------------------------------------------*");
        System.out.printf(" T??n s???n ph???m %25s\n",product.getProductName());
        System.out.printf(" M?? s???n ph???m: %-15s Gi?? ti???n b??n: %-20f  Tr???ng th??i: %-15s\n",product.getProductId(),product.getExportPrice(),status);
        System.out.print(" Hoa ???????c s??? d???ng: ");

        for (int i = 0; i < product.getListFlower().size(); i++) {
            System.out.print(product.getListFlower().get(i).getFlower().getFlowerName() + " (S??? l?????ng: " +product.getListFlower().get(i).getQuantity() +")  ");
        }
        System.out.print("\n");
        System.out.printf(" M?? t??? %25s: ",product.getContent());
        System.out.print("\n");
        System.out.println("*-----------------------------------------------------------------------------------*");
    }

    @Override
    public boolean delete(String str) {
        List<Product> productList = readFromFile();
        for (Product product :productList) {
            if (product.getProductId().equals(str)){
                product.setProductStatus(false);

            }
        }
        return writeFromFile(productList);
    }
    public static void inputProduct (Scanner scanner){
        ProductImpl productImpl = new ProductImpl();
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
            Product product = new Product();
            product = productImpl.inputData(scanner);
            boolean check = productImpl.create(product);
            if (check){
                System.out.println("???? th??m m???i th??nh c??ng !");
            }else {
                System.err.println("Th??m m???i th???t b???i !!!");
            }
        }
    }
    public static void displayProduct (){
        ProductImpl productImpl = new ProductImpl();
        List<Product> list = readFromFile();
        for (Product product: list) {
            productImpl.displayData(product);
        }
    }
    public static void deleteProduct (Scanner scanner){
        ProductImpl productImpl = new ProductImpl();
        List<Product> list = readFromFile();
        while (true){
            System.out.println("Nh???p v??o Id hoa c???n s???a : ");
            String id = scanner.nextLine();
            boolean check = shopValidate.checkEmptyString(id);
            if (check){
                check = productImpl.delete(id);
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

    @Override
    public void searhByName(String str) {

    }
}
