import java.awt.Color;

public class SeamCarver {

	private EnergyGrid energyGrid;
	private int[][] image;
	private AcyclicShortestPath acyclicShortestPath;

	public SeamCarver(Picture picture) {
		buildImage(picture);
		energyGrid = new EnergyGrid(this);
		acyclicShortestPath = new AcyclicShortestPath(/*energyGrid*/);
	}

	private void buildImage(Picture picture) {

		image = new int[picture.height()][picture.width()];
		for (int row = 0; row < height(); row++) {
			for (int col = 0; col < width(); col++) {
				image[row][col] = picture.get(col, row).getRGB();
			}
		}
	}

	// current picture
	public Picture picture() {
		int height = image.length;
		int width = image[0].length;
		Picture currentPicutre = new Picture(width, height);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				currentPicutre.set(col, row, new Color(image[row][col]));
			}
		}
		return currentPicutre;
	}

	// width of current picture
	public int width() {
		return image[0].length;
	}

	// height of current picture
	public int height() {
		return image.length;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (coOrdinatesAreOutOfBounds(x, y)) {
			throw new IndexOutOfBoundsException("Col:" + x + " Row:" + y
					+ " width():" + this.width() + " height:" + this.height());
		}

		if (borderPixel(x, y)) {
			return 255 * 255 + 255 * 255 + 255 * 255;
		}

		return DeltaXSquared(x, y) + DeltaYSquared(x, y);
	}

	private boolean borderPixel(int x, int y) {
		if (x == width() - 1 || y == height() - 1 || x == 0 || y == 0) {
			return true;
		}
		return false;
	}

	private boolean coOrdinatesAreOutOfBounds(int x, int y) {
		if (x < 0 || x >= this.width() || y < 0 || y >= this.height()) {
			return true;
		}
		return false;
	}

	private double DeltaXSquared(int x, int y) {

		int colorOfPixelOneRight = image[y][x + 1];
		int colorOfPixelOneLeft = image[y][x - 1];
		return absDifferenceSquared(getRed(colorOfPixelOneRight),
				getRed(colorOfPixelOneLeft))
				+ absDifferenceSquared(getGreen(colorOfPixelOneRight),
						getGreen(colorOfPixelOneLeft))
				+ absDifferenceSquared(getBlue(colorOfPixelOneRight),
						getBlue(colorOfPixelOneLeft));
	}

	private double DeltaYSquared(int x, int y) {

		int colorOfPixelOneDown = image[y + 1][x];
		int colorOfPixelOneUp = image[y - 1][x];
		return absDifferenceSquared(getRed(colorOfPixelOneDown),
				getRed(colorOfPixelOneUp))
				+ absDifferenceSquared(getGreen(colorOfPixelOneDown),
						getGreen(colorOfPixelOneUp))
				+ absDifferenceSquared(getBlue(colorOfPixelOneDown),
						getBlue(colorOfPixelOneUp));
	}

	private double absDifferenceSquared(int previous, int next) {
		return Math.abs(previous - next) * Math.abs(previous - next);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {

		int[] verticalSeam = acyclicShortestPath.getShortestHorizontalPath(energyGrid);
		return verticalSeam;

	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int[] verticalSeam = acyclicShortestPath.getShortestVerticalPath(energyGrid);
		return verticalSeam;
	}

	public void removeHorizontalSeam(int[] a) {

		if (image.length <= 1 || a.length > image[0].length
				|| isInValidInput(a)) {
			throw new IllegalArgumentException();
		}

		int height = image.length;
		int width = image[0].length;
		int[][] targetedImage = new int[height - 1][width];
		for (int col = 0; col < targetedImage[0].length; col++) {
			int foundColToDelete = 0;
			for (int row = 0; row < targetedImage.length; row++) {
				if (a[col] == row) {
					foundColToDelete = 1;
				}
				targetedImage[row][col] = image[row + foundColToDelete][col];
			}
		}
		image = targetedImage;

		energyGrid.updateEnergyGridPostHorizontalSeamRemoval(a);
		acyclicShortestPath.reCalculateHorizontalShortestPathGraph(energyGrid,
				a);

	}

	// remove vertical seam from picture
	public void removeVerticalSeam(int[] a) {

		if (image[0].length <= 1 || a.length > image.length
				|| isInValidInput(a)) {
			throw new IllegalArgumentException();
		}

		int height = image.length;
		int width = image[0].length;
		int[][] targetedImage = new int[height][width - 1];
		for (int row = 0; row < targetedImage.length; row++) {
			int foundColToDelete = 0;
			for (int col = 0; col < targetedImage[0].length; col++) {
				if (a[row] == col) {
					foundColToDelete = 1;
				}
				targetedImage[row][col] = image[row][col + foundColToDelete];
			}
		}
		image = targetedImage;

		energyGrid.updateEnergyGridPostVerticalSeamRemoval(a);
		acyclicShortestPath.reCalculateVerticalShortestPathGraph(energyGrid, a);

	}


	private boolean isInValidInput(int[] a) {

		for (int item = 0; item < a.length - 1; item++) {
			if (Math.abs(a[item] - a[item + 1]) > 1) {
				return true;
			}
		}
		return false;
	}

	private int getRed(int value) {
		int redBitMask = 0x00FF0000;
		int tempValue = value & redBitMask;
		return tempValue >> 16;
	}

	private int getGreen(int value) {
		int greenBitMask = 0x0000FF00;
		int tempValue = value & greenBitMask;
		return tempValue >> 8;
	}

	private int getBlue(int value) {
		int blueBitMask = 0x000000FF;
		return value & blueBitMask;
	}
}
