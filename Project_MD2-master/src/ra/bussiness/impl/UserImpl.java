package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.IShop;
import ra.bussiness.design.IUser;
import ra.bussiness.entity.User;
import ra.bussiness.file.DataUrl;
import ra.bussiness.file.FileAll;

import java.util.*;

public class UserImpl implements IShop <User , String>, IUser {
    public static List<User> readFromFile (){
        FileAll<User> fileAll = new FileAll<>();
        List<User> listUser =  fileAll.readFromFile(DataUrl.USER_URL);
        return listUser;
    }
    public static boolean writeFromFile (List<User> list){
        FileAll<User> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,DataUrl.USER_URL);
        return returnData;
    }


    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser = new ArrayList<>();
        }
        listUser.add(user);
        boolean result = writeFromFile(listUser);
        return result;
    }

    @Override
    public boolean update(User user) {
        List<User> listUser = readFromFile();
        boolean returnData = false;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId()==(user.getUserId()) ) {
                //Thuc hien cap nhat
                listUser.set(i, user);
                returnData = true;
                break;
            }
        }
        boolean result = writeFromFile(listUser);
        if (result && returnData) {
            return true;
        }
        return false;
    }





    @Override
    public List<User> findAll() {
        return readFromFile();
    }

    @Override
    public User inputData(Scanner scanner) {
        List<User> listUser = readFromFile();
        if (listUser.size()==0){
            listUser = new ArrayList<>();
        }
        User user = new User();
        user.setUserId(listUser.size()+1);
        do {
            System.out.print("Nhập vào tên tài khoản: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkUserNameLength(name);
            if (check){
                check = shopValidate.checkValidateUserName(name);
                if (check){
                    for (User userEx :listUser) {
                        if (userEx.getUserName().equals(name)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        user.setUserName(name);
                        break;
                    }else {
                        System.err.println(ShopMessage.ALREAD_THIS_USERNAME);
                    }
                }
            }else {
                System.err.println(ShopMessage.USER_NAME_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập vào mật khẩu: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
            if (check){
                System.out.print("Nhập lại mật khẩu: ");
                String repeatPasswood = scanner.nextLine();
                check = shopValidate.checkPassword(repeatPasswood);
                if (check){
                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
                }
            }else {
                System.err.println(ShopMessage.PASSWORD_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập họ và tên của bạn: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmptyString(name);
            if (check){
                user.setFullName(name);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.print("Nhập vào email:");
            String email = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmail(email);
            if (check){
                user.setEmail(email);
                break;
            }else {
                System.err.println(ShopMessage.EMAIL_WRONG);
            }
        }while (true);
        do {
            System.out.print("Nhập vào số điện thoại (Số điện thoại bắt đầu bằng 84) :  ");
            String phoneNumber = scanner.nextLine();
            System.out.println("\n");
            boolean checkPhone = shopValidate.checkPhoneNumber(phoneNumber);
               if (checkPhone){
                    user.setPhoneNumber(phoneNumber);
                    break;
                }else {
                    System.err.println(ShopMessage.PHONE_NUMBER_WRONG);
                }

        }while (true);
        Date date = new Date();
        user.setUserStatus(true);
        user.setDate(date);
        return user;

    }

    public User inputDataNew(User user,Scanner scanner) {
        List<User> listUser = readFromFile();

        do {
            System.out.print("Nhập vào tên tài khoản: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkUserNameLength(name);
            if (check){
                check = shopValidate.checkValidateUserName(name);
                if (check){
                    for (User userEx :listUser) {
                        if (userEx.getUserName().equals(name)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        user.setUserName(name);
                        break;
                    }else {
                        System.err.println(ShopMessage.ALREAD_THIS_USERNAME);
                    }
                }
            }else {
                System.err.println(ShopMessage.USER_NAME_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập vào mật khẩu: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
            if (check){
                System.out.print("Nhập lại mật khẩu: ");
                String repeatPasswood = scanner.nextLine();
                check = shopValidate.checkPassword(repeatPasswood);
                if (check){
                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
                }
            }else {
                System.err.println(ShopMessage.PASSWORD_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập họ và tên của bạn: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmptyString(name);
            if (check){
                user.setFullName(name);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.print("Nhập vào email:");
            String email = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmail(email);
            if (check){
                user.setEmail(email);
                break;
            }else {
                System.err.println(ShopMessage.EMAIL_WRONG);
            }
        }while (true);
        do {
            System.out.print("Nhập vào số điện thoại (Số điện thoại bắt đầu bằng 84) :  ");
            String phoneNumber = scanner.nextLine();
            System.out.println("\n");
            boolean checkPhone = shopValidate.checkPhoneNumber(phoneNumber);
            if (checkPhone){
                user.setPhoneNumber(phoneNumber);
                break;
            }else {
                System.err.println(ShopMessage.PHONE_NUMBER_WRONG);
            }

        }while (true);
        Date date = new Date();
        user.setUserStatus(true);
        user.setDate(date);
        return user;

    }

    @Override
    public void displayData(User member) {
        List<User> list = readFromFile();
        String status = "";
        if (member.isUserStatus()){
            status = "Còn hoạt động";
        }else {
            status = "không còn hoạt động";
        }
        String permision = "" ;
        if (member.isPermission()==true){
            permision = "Admin";
        }else {
            permision = "Member";
        }
        System.out.println("*-----------------------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf(" Mã tài khoản:%-20d  Tên tài khoản: %-20s Họ và tên: %-20s Số điện thoại: %-20s \n",member.getUserId(),member.getUserName(),member.getFullName(),member.getPhoneNumber());
        System.out.printf(" Email: %-25s Loại tài khoản: %-20s Trạng thái: %-20s Ngày đăng ký: %-20s\n",member.getEmail(),permision,status,member.getDate() );
        System.out.println("*-----------------------------------------------------------------------------------------------------------------------------------------*");

    }

    @Override
    public boolean delete(String str) {
      List<User> userList = readFromFile();
        for (User user :userList) {
            if (user.getUserName().equals(str)){
                    user.setUserStatus(false);

            }
        }return writeFromFile(userList);

    }
    @Override
    public void searchByName(String str) {
        List<User> list = readFromFile();
        for (User user :list) {
            if (user.getUserName().contains(str)||user.getFullName().contains(str)){
                displayData(user);
            }
        }
    }

    public static void displayUser (){
        UserImpl userImpl = new UserImpl();
        List<User> userList = readFromFile();
        for (User user :userList) {
            userImpl.displayData(user);
        }
    }
    public static void addAdminUser (Scanner scanner){
        List<User> userList = readFromFile();
        User user = new User();
        UserImpl userImpl = new UserImpl();
        do {
            System.out.print("Nhập vào tên tài khoản: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkUserNameLength(name);
            if (check){
                check = shopValidate.checkValidateUserName(name);
                if (check){
                    for (User userEx :userList) {
                        if (userEx.getUserName().equals(name)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        user.setUserName(name);
                        break;
                    }else {
                        System.err.println(ShopMessage.ALREAD_THIS_USERNAME);
                    }
                }
            }else {
                System.err.println(ShopMessage.USER_NAME_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập vào mật khẩu: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
            if (check){
                System.out.print("Nhập lại mật khẩu: ");
                String repeatPasswood = scanner.nextLine();
                check = shopValidate.checkPassword(repeatPasswood);
                if (check){
                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
                }
            }else {
                System.err.println(ShopMessage.PASSWORD_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập họ và tên của bạn: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmptyString(name);
            if (check){
                user.setFullName(name);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.print("Nhập vào email:");
            String email = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkEmail(email);
            if (check){
                user.setEmail(email);
                break;
            }else {
                System.err.println(ShopMessage.EMAIL_WRONG);
            }
        }while (true);
        do {
            System.out.print("Nhập vào số điện thoại (Số điện thoại bắt đầu bằng 84) :  ");
            String phoneNumber = scanner.nextLine();
            System.out.println("\n");
            boolean checkPhone = shopValidate.checkPhoneNumber(phoneNumber);
            if (checkPhone){
                user.setPhoneNumber(phoneNumber);
                break;
            }else {
                System.err.println(ShopMessage.PHONE_NUMBER_WRONG);
            }

        }while (true);
        Date date = new Date();
        user.setDate(date);
        user.setPermission(true);
        boolean check = userImpl.create(user);
        if (check){
            System.out.println("Thêm tài khoản admin mới thành công !");
        }else {
            System.err.println("Thêm mới thất bại");
        }
    }
    public static void updateUserStatus (Scanner scanner){
        UserImpl userImpl = new UserImpl();
        do {
            System.out.println("Nhập vào tên tài khoản khách hàng cần cập nhật ");
            String name = scanner.nextLine();
            boolean check = userImpl.delete(name);
            if (check){
                System.out.println("Đã thay đổi trạng thái thành công ! ");
                break;
            }else {
                System.err.println("Không tìm thấy tên người dùng này vui lòng thử lại !!!! ");
            }
        }while (true);
    }
    public static void searhUserByNameOrFullName(Scanner scanner){
        UserImpl userImpl = new UserImpl();
        System.out.println("Nhập vào tên đăng nhập hoặc tên đầy đủ của người dùng ");
        String name = scanner.nextLine();
        userImpl.searchByName(name);
    }
    }

