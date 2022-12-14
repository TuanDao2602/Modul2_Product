package ra.presentation;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.Flower;
import ra.bussiness.impl.FlowerImpl;

import java.util.List;
import java.util.Scanner;

public class FlowerMenu {
    public static  void flowerMenu (Scanner scanner){
        FlowerImpl flowerImp = new FlowerImpl();
        boolean exit = true;
        do {
            System.out.println("|----------------Flower-------------------|");
            System.out.println("| 1. Danh sách các loài hoa               |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 2. Tạo mới loài hoa                     |");
            System.out.println("|-----------------------------------------|");
            System.out.println("|3. Cập nhật thông tin loài hoa           |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 4. Xóa loài hoa(Cập nhật thành hết hoa) |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 5. Tìm kiếm loài hoa theo tên hoặc giá  |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| 6. thoát                                |");
            System.out.println("|-----------------------------------------|");
            System.out.println(" Sự lựa chọn của bạn là : ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    FlowerImpl.displayFlower();
                    break;
                case 2:
                    FlowerImpl.inputFlower(scanner);
                    break;
                case 3:

                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    String idUpdate =scanner.nextLine();
                    List<Flower> listFlower = flowerImp.readFromFile();
                    for (Flower flower: listFlower) {
                        if (flower.getFlowerId().equals(idUpdate)){
                            flowerImp.update(flowerImp.newInputData(flower,scanner));
                        }
                    }
                    break;
                case 4:
                    FlowerImpl.deleteFlower(scanner);
                    break;
                case 5:
                    boolean exit1 = true;
                    do {
                        System.out.println("nhập vào id hoặc giá sản phẩm để tìm kiếm");
                        System.out.println("1. Nhập vào id của loài hoa để tìm kiếm");
                        System.out.println("2. Nhập vào giá của loài hoa để tìm kiếm");
                        System.out.println("3. thoát");
                        System.out.println("sự lựa chọn của bạn là :");
                        int choice1 = Integer.parseInt(scanner.nextLine());

                        switch (choice1){
                            case 1:
                                System.out.println("Nhập vào tên của Loài hoa cần tìm kiếm");
                                String searchName = scanner.nextLine();
                                flowerImp.searchByName(searchName);
                                break;
                            case 2:
                                System.out.println("Nhập vào giá tiền của loài hoa cần tìm kiếm");
                                float searchPrice = Float.parseFloat(scanner.nextLine());
                                flowerImp.searchByPrice(searchPrice);
                                break;
                            case 3:
                                exit1 =false;
                                break;
                            default:
                                System.out.println("nhập 1-3");
                                break;
                        }
                    }while (exit1);


                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }
}
