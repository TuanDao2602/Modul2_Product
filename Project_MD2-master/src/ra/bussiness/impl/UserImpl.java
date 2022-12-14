package ra.bussiness.impl;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.shopValidate;
import ra.bussiness.design.IShop;
import ra.bussiness.design.IUser;
import ra.bussiness.entity.TypeOfFlower;
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
            System.out.print("Nh???p v??o t??n t??i kho???n: ");
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
            System.out.print("Nh???p v??o m???t kh???u: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
            if (check){
                System.out.print("Nh???p l???i m???t kh???u: ");
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
            System.out.print("Nh???p h??? v?? t??n c???a b???n: ");
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
            System.out.print("Nh???p v??o email:");
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
            System.out.print("Nh???p v??o s??? ??i???n tho???i (S??? ??i???n tho???i b???t ?????u b???ng 84) :  ");
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
            System.out.print("Nh???p v??o t??n t??i kho???n m???i: ");
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
            System.out.print("Nh???p v??o m???t kh???u m???i: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
//            if (check){
//                System.out.print("Nh???p l???i m???t kh???u m???i: ");
//                String repeatPasswood = scanner.nextLine();
//                check = shopValidate.checkPassword(repeatPasswood);
                if (check){
//                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
//                }
//            }else {
//                System.err.println(ShopMessage.PASSWORD_LENGTH);
//            }
        }while (true);
        do {
            System.out.print("Nh???p h??? v?? t??n m???i c???a b???n: ");
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
            System.out.print("Nh???p v??o email m???i:");
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
            System.out.print("Nh???p v??o s??? ??i???n tho???i m???i:  ");
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
            status = "C??n ho???t ?????ng";
        }else {
            status = "kh??ng c??n ho???t ?????ng";
        }
        String permision = "" ;
        if (member.isPermission()==true){
            permision = "Admin";
        }else {
            permision = "Member";
        }
        System.out.println("*----------------------------------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf(" M?? t??i kho???n:%-20d  T??n t??i kho???n: %-20s H??? v?? t??n: %-20s S??? ??i???n tho???i: %-20s \n",member.getUserId(),member.getUserName(),member.getFullName(),member.getPhoneNumber());
        System.out.printf(" Email: %-25s Lo???i t??i kho???n: %-20s Tr???ng th??i: %-20s Ng??y ????ng k??: %-20s\n",member.getEmail(),permision,status,member.getDate() );
        System.out.println("*----------------------------------------------------------------------------------------------------------------------------------------------------*");

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
//        List<User> userList = readFromFile();
//        User user = new User();
        UserImpl userImpl = new UserImpl();
//        User user1 = new User();
//        user1.setUserId(userList.size()+1);

        List<User> usersList = readFromFile();
        if (usersList==null){
            usersList = new ArrayList<>();
        }
        User user = new User();
        user.setUserId(usersList.size()+1);
        do {
            System.out.print("Nh???p v??o t??n t??i kho???n: ");
            String name = scanner.nextLine();

            System.out.print("\n");
            boolean check = shopValidate.checkUserNameLength(name);
            if (check){
                check = shopValidate.checkValidateUserName(name);
                if (check){
                    for (User userEx :usersList) {
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
            System.out.print("Nh???p v??o m???t kh???u: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = shopValidate.checkPassword(password);
            if (check){
                System.out.print("Nh???p l???i m???t kh???u: ");
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
            System.out.print("Nh???p h??? v?? t??n c???a b???n: ");
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
            System.out.print("Nh???p v??o email:");
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
            System.out.print("Nh???p v??o s??? ??i???n tho???i  :  ");
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
        user.setUserStatus(true);
        boolean check = userImpl.create(user);
        if (check){
            System.out.println("Th??m t??i kho???n admin m???i th??nh c??ng !");
        }else {
            System.err.println("Th??m m???i th???t b???i");
        }
    }
    public static void updateUserStatus (Scanner scanner){
        UserImpl userImpl = new UserImpl();
        do {
            System.out.println("Nh???p v??o t??n t??i kho???n kh??ch h??ng c???n c???p nh???t ");
            String name = scanner.nextLine();
            boolean check = userImpl.delete(name);
            if (check){
                System.out.println("???? thay ?????i tr???ng th??i th??nh c??ng ! ");
                break;
            }else {
                System.err.println("Kh??ng t??m th???y t??n ng?????i d??ng n??y vui l??ng th??? l???i !!!! ");
            }
        }while (true);
    }
    public static void searhUserByNameOrFullName(Scanner scanner){
        UserImpl userImpl = new UserImpl();
        System.out.println("Nh???p v??o t??n ????ng nh???p ho???c t??n ?????y ????? c???a ng?????i d??ng ");
        String name = scanner.nextLine();
        userImpl.searchByName(name);
    }
    }

