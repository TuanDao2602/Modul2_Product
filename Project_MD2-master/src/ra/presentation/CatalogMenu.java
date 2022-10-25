package ra.presentation;

import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.Catalog;
import ra.bussiness.impl.CatalogImpl;

import java.util.List;
import java.util.Scanner;

public class CatalogMenu {
    public static void catalogMenu (Scanner scanner){
        boolean exit = true;
        CatalogImpl catImp = new CatalogImpl();

        do {
            System.out.println("|-----------CatalogMenu-------------|");
            System.out.println("| 1. Danh mục sản phẩm              |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 2. Tạo mới danh mục sản phẩm      |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 3. Cập nhật danh mục sản phẩm     |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 4. Xóa danh mục sản phẩm          |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 5. Tìm kiếm danh mục theo tên     |");
            System.out.println("|-----------------------------------|");
            System.out.println("| 6. Thoát                          |");
            System.out.println("|-----------------------------------|");
            System.out.println("Sự lựa chọn của bạn là : ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    CatalogImpl.displayCatalog();
                    break;
                case 2:
                    CatalogImpl.inputCatalog(scanner);
                    break;
                case 3:

                    System.out.println("mời bạn nhập id muốn cập nhật ");
                    String idUpdate = (scanner.nextLine());
                    List<Catalog> listCatalog = catImp.readFromFile();
                    for (Catalog catalog: listCatalog) {
                        if (catalog.getCatalogId().equals(idUpdate)){
                            catImp.update(catImp.inputDataNew(catalog,scanner));
                        }
                    }
                    break;
                case 4:
                    CatalogImpl.deleteCatalog(scanner);
                    break;
                case 5:
                    CatalogImpl.searchCatalogByName(scanner);
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
