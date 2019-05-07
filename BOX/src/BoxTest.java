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
			System.out.println("상자의 크기를 결정하려 합니다.");
			do {
				System.out.print("상자의 가로를 입력하세요 : ");
				height = input.nextInt();
				if (height < 0) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시입력해주세요");
				}
			} while (height < 0);
			do {
				System.out.print("상자의 세로를 입력하세요 : ");
				width = input.nextInt();
				if (width < 0) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시입력해주세요");
				}
			} while (width < 0);
			do {
				System.out.print("상자의 깊이를 입력하세요 : ");
				depth = input.nextInt();
				if (depth < 0) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시입력해주세요");
				}
			} while (depth < 0);

			System.out.print("상자는 비었나요? (Y/N) : ");
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

			System.out.print("\n다시 하시겠습니까? (Y/N) : ");
			answer = input1.nextLine().charAt(0);

		} while (answer == 'y' || answer == 'Y');

	}

}
