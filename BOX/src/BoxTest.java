import java.util.Scanner;

public class BoxTest {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);

		Box box = new Box();

		int width;
		int height;
		int depth;
		char answer;
		char choice;
		boolean empty;

		do {
			System.out.println("������ ũ�⸦ �����Ϸ� �մϴ�.");
			do {
				System.out.print("������ ���θ� �Է��ϼ��� : ");
				height = input.nextInt();
				if (height < 0) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("�ٽ��Է����ּ���");
				}
			} while (height < 0);
			do {
				System.out.print("������ ���θ� �Է��ϼ��� : ");
				width = input.nextInt();
				if (width < 0) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("�ٽ��Է����ּ���");
				}
			} while (width < 0);
			do {
				System.out.print("������ ���̸� �Է��ϼ��� : ");
				depth = input.nextInt();
				if (depth < 0) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("�ٽ��Է����ּ���");
				}
			} while (depth < 0);

			System.out.print("���ڴ� �������? (Y/N) : ");
			choice = input1.nextLine().charAt(0);

			if (!(choice == 'y' || choice == 'Y')) {
				empty = true;
			} else {
				empty = false;
			}
			box.setHeight(height);
			box.setWidth(width);
			box.setDepth(depth);
			box.setEmpty(empty);

			System.out.println("\n" + box);

			System.out.print("\n�ٽ� �Ͻðڽ��ϱ�? (Y/N) : ");
			answer = input1.nextLine().charAt(0);

		} while (answer == 'y' || answer == 'Y');

	}

}
