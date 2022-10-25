package ra.presentation;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Product;
import ra.bussiness.impl.ProductImpl;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    public static void productMenu (Scanner scanner){
        ProductImpl productImp =new ProductImpl();
        boolean exit = true;
        do {
            System.out.println("|--------------Products---------------|");
            System.out.println("| 1. Danh sách sản phẩm.              |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 2. Tạo mới sản phẩm.                |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 3. Cập nhật thông tin sản phẩm.     |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 4. Xóa loài hoa                     |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 5. Thoát.                           |");
            System.out.println("|-------------------------------------|");
            System.out.print(  "| Lựa chọn của bạn:                   |");
            System.out.println("|-------------------------------------|");
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
                    ProductImpl.inputProduct(scanner);
                    break;
                case 3:

                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    String idUpdate = (scanner.nextLine());
                    List<Product> listProduct = productImp.readFromFile();
                    for (Product product: listProduct) {
                        if (product.getProductId().equals(idUpdate)){
                            productImp.update(productImp.inputDataNew(product,scanner));
                        }
                        break;
                    }

                    break;
                case 4:
                    ProductImpl.deleteProduct(scanner);
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5 !!!");
            }
        }while (exit);
    }
}
