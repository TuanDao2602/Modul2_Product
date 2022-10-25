package ra.presentation;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.file.FileAll;
import ra.bussiness.impl.FlowerImpl;
import ra.bussiness.impl.TypeOfFlowerImpl;
import ra.bussiness.impl.UserImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FlowerTypesMenu {
//    static UserImpl userImpl = new UserImpl();
//    static TypeOfFlowerImpl tOFlowerImpl = new TypeOfFlowerImpl();
//    static FileAll fileAll = new FileAll();
    static   TypeOfFlowerImpl typeOfFlowerImpl = new TypeOfFlowerImpl();
//    static FlowerImpl  flowerImpl = new FlowerImpl();
    public static void typeOfFlowerManagement (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("|-----------FlowerTypesMenu-----------|");
            System.out.println("| 1. Danh sách các loại hoa           |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 2. Tạo mới loại hoa                 |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 3. Cập nhật thông tin loại hoa      |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 4. Xóa loại hoa                     |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 5. Tìm kiếm theo tên hoặc mã        |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 6. Thoát                            |");
            System.out.println("|-------------------------------------|");
            System.out.println("sự lựa chọn của bạn là");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    displayTypeOfFlower();
                    break;
                case 2:
                    inputTypeOfFlower(scanner);
                    break;
                case 3:


                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    List<TypeOfFlower> listFlowerTypes = typeOfFlowerImpl.readFromFile();
                    for (TypeOfFlower flowerTypes: listFlowerTypes) {
                        if (flowerTypes.getTypeFlowerId()==idUpdate){
                            typeOfFlowerImpl.update(typeOfFlowerImpl.inputDataNew(flowerTypes,scanner));
                        }
                    }

                    break;
                case 4:
                    deleteTypeOfFlower(scanner);
                    break;
                case 5:
                    searchTypeFlowerByNameOrId(scanner);
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }

    public static void displayTypeOfFlower (){
        List<TypeOfFlower> typeOfFlowerList = TypeOfFlowerImpl.readFromFile();
        Collections.sort(typeOfFlowerList, new Comparator<TypeOfFlower>() {
            @Override
            public int compare(TypeOfFlower o1, TypeOfFlower o2) {
                return (int) (o1.getTypeFlowerName().charAt(5) - o2.getTypeFlowerName().charAt(5));
            }
        });
        for (TypeOfFlower typeOfFlower: typeOfFlowerList) {
            typeOfFlowerImpl.displayData(typeOfFlower);
        }
    }

    public static void inputTypeOfFlower (Scanner scanner){
        System.out.println("Nhập vào số lượng loại hoa bạn muốn nhập lần này: ");
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
            System.out.println("Nhập vào loại hoa thứ " + (i+1));
            TypeOfFlower typeOfFlower = new TypeOfFlower();
            typeOfFlower = typeOfFlowerImpl.inputData(scanner);
            boolean check = typeOfFlowerImpl.create(typeOfFlower);
            if (check){
                System.out.println("Đã thêm mới thành công !");
            }else {
                System.err.println("Thêm mới thất bại !!!");
            }
        }
    }

    public static void searchTypeFlowerByNameOrId (Scanner scanner){
        do {
            System.out.println("Nhập vào tên loại hoa hoặc mã loại hoa ");
            String search = scanner.nextLine();
            boolean check = typeOfFlowerImpl.searchByNameOrId(search,search);
            if (check){
                break;
            }else {
                System.err.println("Vui lòng nhập lại !!!");
            }
        }while (true);
    }

    public static void deleteTypeOfFlower (Scanner scanner){

        int id = 0;
        while (true){
            try {
                System.out.println("Nhập vào mã loại hoa cần xóa : ");
                id = Integer.parseInt(scanner.nextLine());
                boolean check = typeOfFlowerImpl.delete(id);
                if (check){
                    System.out.println("Đã xóa thành công !");
                    break;
                }else {
                    System.err.println("Xóa không thành công ");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }
    }


}
