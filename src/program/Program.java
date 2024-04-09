package program;

import dao.CategoryDAO;
import dao.CategoryDaoItf;
import entity.Category;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryDAO categoryDAO = new CategoryDaoItf();
        do {
            System.out.println("====QUAN LY DANH MUC====");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Sửa danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Tìm kiếm danh mục");
            System.out.println("6. Thooát");
            System.out.println("Mời bạn chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    List<Category> categories = categoryDAO.getAllCategory();
                    for (Category category : categories) {
                        System.out.println(category.toString());
                    }
                    break;
                case 2:
                    Category category = new Category();
                    System.out.println("Nhập thông tin danh mục");
                    category.inputData(scanner);
                    if(categoryDAO.addCategory(category)){
                        System.out.println("Thêm mới thành công");
                    } else {
                        System.out.println("Thêm mới thất bại");
                    }
                    break;
                case 3:
                    System.out.println("mời nhập id danh mục cần sửa");
                    int id = Integer.parseInt(scanner.nextLine());
                    Category categoryEdit = categoryDAO.findById(id);
                    if(categoryEdit == null){
                        System.err.println("Rất tiêc không tìm thấy danh mục nào có id = "+id);
                    } else {
                        System.out.println("Có phải bạn muốn thay đổi thông tin của danh mục này không ?");
                        System.out.println(categoryEdit.toString());
                        System.out.println("Nhập vào thông tin mới");
                        System.out.println("Nhập vào tên danh mục :");
                        categoryEdit.setCategoryName(scanner.nextLine());
                        System.out.println("Nhập vào trạng thái danh mục :");
                        categoryEdit.setCategoryStatus(Boolean.parseBoolean(scanner.nextLine()));
                        if (categoryDAO.updateCategory(categoryEdit)){
                            System.out.println("Cập nhật thành công");
                        }else {
                            System.err.println("Cập nhật thất bại");
                        }
                    }
                    break;
                case 4:
                    System.out.println("mời nhập id danh mục cần xoá");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    Category categorDelete = categoryDAO.findById(idDelete);
                    if (categorDelete != null){
                        System.out.println("Có phải bạn muốn xoá thông tin của danh mục này không ?");
                        System.out.println(categorDelete.toString());
                        System.out.println("Bạn có chắc chắn muốn xoá không? c/k");
                        String chose = scanner.nextLine();
                        if (chose.equalsIgnoreCase("c")){
                            categoryDAO.deleteCategory(idDelete);
                            System.out.println("Đã xoá xong");
                        } else {
                            System.err.println("huỷ xoá danh mục");
                        }
                    } else {
                        System.err.println("Rất tiêc không tìm thấy danh mục nào có id = "+idDelete);
                    }
                    break;
                case 5:
                    System.out.println("Mời bạn nhập tên danh mục muốn tìm kiếm");
                    String keyword = scanner.nextLine();
                    List<Category> list = categoryDAO.searchByName(keyword);
                    int count = 0;
                    for (Category category1 : list) {
                        System.out.println(category1.toString());
                        count++;
                    }
                    if (count == 0){
                        System.err.println("Không tìm thấy danh mục muốn tìm");
                    }
                    break;
                default:
                    System.out.println("Moi chọn lai");
                case 6:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

}
