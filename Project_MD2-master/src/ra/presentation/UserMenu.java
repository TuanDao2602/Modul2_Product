package ra.presentation;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.Product;
import ra.bussiness.entity.User;
import ra.bussiness.impl.UserImpl;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    public  static  void userManagementMenu (Scanner scanner){
        UserImpl userImp = new UserImpl();
        boolean exit = true;
        do {

            System.out.println("|------------------UserMenuAdmin-------------------|");
            System.out.println("| 1. Danh sách tài khoản                           |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 2. Thêm tài khoản quản trị                       |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 3. Cập nhật tài khoản quản trị                   |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 4. Cập nhật trạng thái tài khoản khách hàng      |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 5. Tìm kiếm tài khoản khách hàng theo userName   |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| 6. thoát                                         |");
            System.out.println("|--------------------------------------------------|");
            System.out.println(" sự lựa chọn của bạn là:");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    UserImpl.displayUser();
                    break;
                case 2:
                    UserImpl.addAdminUser(scanner);
                    break;
                case 3:
                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    int idUpdate =Integer.parseInt (scanner.nextLine());
                    List<User> listUser = userImp.readFromFile();
                    for (User user: listUser) {
                        if (user.getUserId()==idUpdate){
                            userImp.update(userImp.inputDataNew(user,scanner));
                        }
                        break;
                    }
                    break;
                case 4:
                    UserImpl.updateUserStatus(scanner);
                    break;
                case 5:
                    UserImpl.searhUserByNameOrFullName(scanner);
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }
}
