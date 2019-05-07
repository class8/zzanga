import java.util.Scanner;

public class Box {

	private int height;
	private int width;
	private int depth;
	private boolean empty;

	public Box() {
		super();
	}

	public Box(int height, int width, int depth) {
		super();
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	public Box(int height, int width, int depth, boolean empty) {
		super();
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.empty = empty;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return "상자의 가로 : " + getHeight() + "\n상자의 세로 : " + getWidth() + "\n상자의 깊이 : " + getDepth() + "\n상자속 : "
				+ (isEmpty() ? "무언가 들어있다." : "비었다.");
	}

}
