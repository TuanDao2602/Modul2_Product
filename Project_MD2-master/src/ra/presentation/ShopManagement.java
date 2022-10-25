package ra.presentation;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.*;
import ra.bussiness.file.FileAll;
import ra.bussiness.impl.*;

import java.io.File;
import java.util.*;

public class ShopManagement {

    static TypeOfFlowerImpl tOFlowerImpl = new TypeOfFlowerImpl();
    static FileAll fileAll = new FileAll();
    static UserImpl userImpl = new UserImpl();

    static FlowerImpl  flowerImpl = new FlowerImpl();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> listUser = new ArrayList<>();
        Date date = new Date();
        User user = new User(1, "ad", "1", "Đào Văn Tuấn", "Daovantuan9999@yahoo.com", "0973523050",1, true ,date);
        listUser.add(user);
        UserImpl.writeFromFile(listUser);
        do {
            System.out.println("|----------Quản Trị Tài Khoản----------|");
            System.out.println("|--------------------------------------|");
            System.out.println("| 1. Đăng nhập                         |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 2. Đăng ký                           |");
            System.out.println("|--------------------------------------|");
            System.out.println("| 3. Thoát                             |");
            System.out.println("|--------------------------------------|");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    do {
                        User user1 = login(scanner);
                        if (user1!=null){
                            if (user1.getPermission()==1){
                                adminMenu(scanner);
                                break;
                            }else {
                                userMenu(scanner);
                                break;
                            }
                        }else {
                            System.err.println("Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng thử lại !!! ");
                        }

                    }while (true);
                    break;
                case 2:
                    regester(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
            }
        }while (true);
    }
    public static void adminMenu(Scanner scanner){
        boolean checkExit = true;
        do {
            System.out.println("|-------------Cửa Hàng Hoa---------------|");
            System.out.println("| 1. Quản trị các loại hoa               |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 2. Quản trị ca loài hoa                |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 3. Quản trị các danh mục sản phẩm      |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 4. Quản trị các sản phẩm bán           |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 5. Quản trị các tài khoản              |");
            System.out.println("|----------------------------------------|");
            System.out.println("| 6. Quản trị các đơn hàng của khách hàng|");
            System.out.println("|----------------------------------------|");
            System.out.println("| 7. Quản trị các phản hồi của khách hàng|");
            System.out.println("|----------------------------------------|");
            System.out.println("| 8. Thoát                               |");
            System.out.println("|----------------------------------------|");
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
                    FlowerTypesMenu.typeOfFlowerManagement(scanner);
                    break;
                case 2:
                    FlowerMenu.flowerMenu(scanner);
                    break;
                case 3:
                    CatalogMenu.catalogMenu(scanner);
                    break;
                case 4:
                    ProductMenu.productMenu(scanner);
                    break;
                case 5:
                    UserMenu.userManagementMenu(scanner);
                    break;
                case 6:
                        OrderMenu.orderMenu(scanner);
                    break;
                case 7:
                    feedback();
                    break;
                case 8:
                    checkExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-8 !!!");
            }

        }while (checkExit);
    }





    public static void userMenu (Scanner scanner){
        boolean checkExit = true;
        do {
            System.out.println("|-------------- Tiệm Hoa Tình Yêu Xin Chào Qúy Khách -------------|");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.println("| 1. Xem danh sách các sản phẩm.                                  |");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.println("| 2. Đặt mua các sản phẩm.                                        |");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.println("| 3. Đổi mật khẩu tài khoản.                                      |");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.println("| 4. Phản hồi với cửa hàng.                                       |");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.println("| 5. Đăng xuất.                                                   |");
            System.out.println("|-----------------------------------------------------------------|");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    ProductImpl.displayProduct();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5 !!!");
            }

        }while (checkExit);
    }
    public static void feedback (){

    }
    public static User login (Scanner scanner){
        List<User> listUser = UserImpl.readFromFile();
        System.out.print("Tên đăng nhập: ");
        String userName =  scanner.nextLine();
        System.out.print("\n");
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("\n");
        for (User user:listUser ) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static void regester(Scanner scanner){
        User user = new User();
         user = userImpl.inputData(scanner);
       boolean check = userImpl.create(user);
       if(check){
           System.out.println("Dang ky thanh cong");
       }else {
           System.err.println("Dang ky khong thanh cong");
       }
    }





}
