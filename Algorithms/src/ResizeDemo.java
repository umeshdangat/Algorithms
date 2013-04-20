/*************************************************************************
 * Compilation: javac ResizeDemo.java Execution: java ResizeDemo input.png
 * columnsToRemove rowsToRemove Dependencies: SeamCarver.java SCUtility.java
 * Picture.java Stopwatch.java StdDraw.java
 * 
 * 
 * Read image from file specified as command line argument. Use SeamCarver to
 * remove number of rows and columns specified as command line arguments. Show
 * the images in StdDraw and print time elapsed to screen.
 * 
 *************************************************************************/

public class ResizeDemo {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out
					.println("Usage:\njava ResizeDemo [image filename] [num cols to remove] [num rows to remove]");
			return;
		}

		Picture inputImg = new Picture(args[0]);
		int removeColumns = Integer.parseInt(args[1]);
		int removeRows = Integer.parseInt(args[2]);

		System.out.printf("image is %d columns by %d rows\n", inputImg.width(),
				inputImg.height());
		SeamCarver sc = new SeamCarver(inputImg);

		Stopwatch sw = new Stopwatch();

		for (int i = 0; i < removeRows; i++) {
			int[] horizontalSeam = sc.findHorizontalSeam();
			sc.removeHorizontalSeam(horizontalSeam);
		}
		System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());

		for (int i = 0; i < removeColumns; i++) {
			int[] verticalSeam = sc.findVerticalSeam();
			sc.removeVerticalSeam(verticalSeam);
		}
		Stopwatch drawOutput = new Stopwatch();
		Picture outputImg = sc.picture();
		double timeToDrawOutput = drawOutput.elapsedTime();

		System.out.printf("new image size is %d columns by %d rows\n",
				sc.width(), sc.height());

		System.out.println("timeToDrawOutput " + timeToDrawOutput);

		System.out.println("Resizing time: " + sw.elapsedTime() + " seconds.");
		/*System.out.println("Energy Grid Array Copy total time: " + sc.getEnergyGridArrayCopyTime() + " seconds.");
		System.out.println("Fix Dist to total time: " + sc.getFixDistToTotalTime() + " seconds.");
		System.out.println("Find vertical seam time: " + sc.getFindVerticalSeamTime()+ " seconds.");
		System.out.println("Find update picture after vertical seam removal time: " + sc.getUpdatePictureTime()+ " seconds.");
		System.out.println("Rebuild of distTo for each transpose total time: " + sc.getBuildDistToTime()+ " seconds.");
		System.out.println("GetEnergyGridTranposeCost : " + sc.getEnergyGridTranposeCost()+ " seconds.");
		System.out.println("GetReducedHeight cost : " + sc.getReduceHeight()+ " seconds.");
		System.out.println("GetReducedWidth cost : " + sc.getReduceWidth()+ " seconds.");*/
		
		inputImg.show();
		outputImg.show();
	}

}