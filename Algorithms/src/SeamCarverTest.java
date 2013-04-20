import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

public class SeamCarverTest {

	@Mock
	private Picture pictureMock;
	@Mock
	private Color colorMockLeft, colorMockRight, colorMockUp, colorMockDown;
	@Mock
	private Color colorMock10, colorMock11, colorMock12, colorMock01,
			colorMock31, colorMock20, colorMock21, colorMock22;

	@Before
	public void initMocks() {
		org.mockito.MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPicture() {
		int height = 100;
		int width = 100;
		Picture testPicture = new Picture(width, height);
		SeamCarver seamCarver = new SeamCarver(testPicture);
		assertEquals(testPicture, seamCarver.picture());
	}

	@Test
	public void testWidth() {
		int height = 101;
		int width = 102;
		Picture testPicture = new Picture(width, height);
		SeamCarver seamCarver = new SeamCarver(testPicture);
		assertEquals(width, seamCarver.width());
	}

	@Test
	public void testHeight() {
		int height = 101;
		int width = 102;
		Picture testPicture = new Picture(width, height);
		SeamCarver seamCarver = new SeamCarver(testPicture);
		assertEquals(height, seamCarver.height());
	}

	@Test
	public void testEnergy() {

		when(pictureMock.width()).thenReturn(3);
		when(pictureMock.height()).thenReturn(4);

		when(pictureMock.get(0, 2)).thenReturn(colorMockLeft);
		when(pictureMock.get(2, 2)).thenReturn(colorMockRight);
		when(pictureMock.get(1, 1)).thenReturn(colorMockUp);
		when(pictureMock.get(1, 3)).thenReturn(colorMockDown);

		when(colorMockRight.getRed()).thenReturn(255);
		when(colorMockRight.getGreen()).thenReturn(205);
		when(colorMockRight.getBlue()).thenReturn(255);

		when(colorMockLeft.getRed()).thenReturn(255);
		when(colorMockLeft.getGreen()).thenReturn(203);
		when(colorMockLeft.getBlue()).thenReturn(51);

		when(colorMockUp.getRed()).thenReturn(255);
		when(colorMockUp.getGreen()).thenReturn(153);
		when(colorMockUp.getBlue()).thenReturn(153);

		when(colorMockDown.getRed()).thenReturn(255);
		when(colorMockDown.getGreen()).thenReturn(255);
		when(colorMockDown.getBlue()).thenReturn(153);

		SeamCarver seamCarver = new SeamCarver(pictureMock);
		assertEquals(52024, seamCarver.energy(1, 2), 0.0001);

	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testEnergyOutOfBoundsXLow() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		seamCarver.energy(-1, row - 1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testEnergyOutOfBoundsXHigh() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		seamCarver.energy(col, row - 1);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testEnergyOutOfBoundsYHigh() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		seamCarver.energy(col - 1, row);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testEnergyOutOfBoundsYLow() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		seamCarver.energy(col - 1, -1);
	}

	@Test
	public void testEnergyColMaxBorderPixel() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		assertEquals(255 * 255 + 255 * 255 + 255 * 255,
				seamCarver.energy(col - 1, row - 2), 0.00001);
	}

	@Test
	public void testEnergyColMinBorderPixel() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		assertEquals(255 * 255 + 255 * 255 + 255 * 255,
				seamCarver.energy(0, row - 2), 0.00001);
	}

	@Test
	public void testEnergyRowMaxBorderPixel() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		assertEquals(255 * 255 + 255 * 255 + 255 * 255,
				seamCarver.energy(col - 2, row - 1), 0.00001);
	}

	@Test
	public void testEnergyRowMinBorderPixel() {
		int row = 4;
		int col = 3;
		Picture picture = new Picture(col, row);
		SeamCarver seamCarver = new SeamCarver(picture);
		assertEquals(255 * 255 + 255 * 255 + 255 * 255,
				seamCarver.energy(col - 2, 0), 0.00001);
	}

	@Test
	public void testFindHorizontalSeam4By3() {
		Picture picture = setPicture();
		SeamCarver seamCarver = new SeamCarver(picture);
		int[] expectedSeam = new int[picture.width()];
		expectedSeam[0] = 0;
		expectedSeam[1] = 1;
		expectedSeam[2] = 0;
		assertArrayEquals(expectedSeam, seamCarver.findHorizontalSeam());
	}

	@Test
	public void testFindVerticalSeam4By3() {
		Picture picture = setPicture();
		SeamCarver seamCarver = new SeamCarver(picture);
		int[] expectedSeam = new int[picture.height()];
		expectedSeam[0] = 0;
		expectedSeam[1] = 1;
		expectedSeam[2] = 1;
		expectedSeam[3] = 0;
		assertArrayEquals(expectedSeam, seamCarver.findVerticalSeam());
	}

	@Test
	public void testFindHorizontalThenVerticalSeam4By3() {
		Picture picture = setPicture();
		SeamCarver seamCarver = new SeamCarver(picture);

		int[] expectedHorizontalSeam = new int[picture.width()];
		expectedHorizontalSeam[0] = 0;
		expectedHorizontalSeam[1] = 1;
		expectedHorizontalSeam[2] = 0;

		assertArrayEquals(expectedHorizontalSeam,
				seamCarver.findHorizontalSeam());

		int[] expectedVerticalSeam = new int[picture.height()];
		expectedVerticalSeam[0] = 0;
		expectedVerticalSeam[1] = 1;
		expectedVerticalSeam[2] = 1;
		expectedVerticalSeam[3] = 0;
		assertArrayEquals(expectedVerticalSeam, seamCarver.findVerticalSeam());
	}
	
	
	@Test
	public void testFindVerticalThenHorizontalSeam4By3() {
		Picture picture = setPicture();
		SeamCarver seamCarver = new SeamCarver(picture);

		int[] expectedHorizontalSeam = new int[picture.width()];
		expectedHorizontalSeam[0] = 0;
		expectedHorizontalSeam[1] = 1;
		expectedHorizontalSeam[2] = 0;
		int[] expectedVerticalSeam = new int[picture.height()];
		expectedVerticalSeam[0] = 0;
		expectedVerticalSeam[1] = 1;
		expectedVerticalSeam[2] = 1;
		expectedVerticalSeam[3] = 0;
		
		assertArrayEquals(expectedVerticalSeam,
				seamCarver.findVerticalSeam());

		
		assertArrayEquals(expectedHorizontalSeam, seamCarver.findHorizontalSeam());
	}

	@Test
	public void testFindVerticalSeam() {

		setMockPictureAndColorPixels();

		SeamCarver seamCarver = new SeamCarver(pictureMock);

		int[] expectedSeam = new int[pictureMock.height()];
		expectedSeam[0] = 0;
		expectedSeam[1] = 1;
		expectedSeam[2] = 1;
		expectedSeam[3] = 0;
		assertArrayEquals(expectedSeam, seamCarver.findVerticalSeam());
	}

	private void setMockPictureAndColorPixels() {
		when(pictureMock.width()).thenReturn(3);
		when(pictureMock.height()).thenReturn(4);

		when(pictureMock.get(0, 1)).thenReturn(colorMock10);
		when(pictureMock.get(1, 1)).thenReturn(colorMock11);
		when(pictureMock.get(2, 1)).thenReturn(colorMock12);

		when(pictureMock.get(0, 2)).thenReturn(colorMock20);
		when(pictureMock.get(1, 2)).thenReturn(colorMock21);
		when(pictureMock.get(2, 2)).thenReturn(colorMock22);

		when(pictureMock.get(1, 0)).thenReturn(colorMock01);
		when(pictureMock.get(1, 3)).thenReturn(colorMock31);

		when(colorMock10.getRed()).thenReturn(255);
		when(colorMock10.getGreen()).thenReturn(153);
		when(colorMock10.getBlue()).thenReturn(51);

		when(colorMock11.getRed()).thenReturn(255);
		when(colorMock11.getGreen()).thenReturn(153);
		when(colorMock11.getBlue()).thenReturn(153);

		when(colorMock12.getRed()).thenReturn(255);
		when(colorMock12.getGreen()).thenReturn(153);
		when(colorMock12.getBlue()).thenReturn(255);

		when(colorMock20.getRed()).thenReturn(255);
		when(colorMock20.getGreen()).thenReturn(203);
		when(colorMock20.getBlue()).thenReturn(51);

		when(colorMock21.getRed()).thenReturn(255);
		when(colorMock21.getGreen()).thenReturn(204);
		when(colorMock21.getBlue()).thenReturn(153);

		when(colorMock22.getRed()).thenReturn(255);
		when(colorMock22.getGreen()).thenReturn(205);
		when(colorMock22.getBlue()).thenReturn(255);

		when(colorMock01.getRed()).thenReturn(255);
		when(colorMock01.getGreen()).thenReturn(101);
		when(colorMock01.getBlue()).thenReturn(153);

		when(colorMock31.getRed()).thenReturn(255);
		when(colorMock31.getGreen()).thenReturn(255);
		when(colorMock31.getBlue()).thenReturn(153);
	}

	@Test
	public void testRemoveHorizontalSeam() {
		Picture originalPicture = setPicture();

		SeamCarver seamCarver = new SeamCarver(originalPicture);
		seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());
		Picture actualPictureAfterCarving = seamCarver.picture();

		assertEquals(originalPicture.width(), actualPictureAfterCarving.width());
		assertEquals(originalPicture.height() - 1,
				actualPictureAfterCarving.height());
		Picture expectedPictureAfterCarving = new Picture(
				originalPicture.width(), originalPicture.height() - 1);
		expectedPictureAfterCarving.set(0, 0, new Color(213));
		expectedPictureAfterCarving.set(0, 1, new Color(214));
		expectedPictureAfterCarving.set(0, 2, new Color(215));

		expectedPictureAfterCarving.set(1, 0, new Color(216));
		expectedPictureAfterCarving.set(1, 1, new Color(218));
		expectedPictureAfterCarving.set(1, 2, new Color(219));

		expectedPictureAfterCarving.set(2, 0, new Color(221));
		expectedPictureAfterCarving.set(2, 1, new Color(222));
		expectedPictureAfterCarving.set(2, 2, new Color(223));

		assertEquals(expectedPictureAfterCarving, actualPictureAfterCarving);

	}

	@Test
	public void testRemoveVerticalSeam() {
		Picture originalPicture = setPicture();
		SeamCarver seamCarver = new SeamCarver(originalPicture);
		seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());

		Picture actualPictureAfterCarving = seamCarver.picture();
		assertEquals(originalPicture.width() - 1,
				actualPictureAfterCarving.width());
		assertEquals(originalPicture.height(),
				actualPictureAfterCarving.height());

		Picture expectedPictureAfterCarving = new Picture(
				originalPicture.width() - 1, originalPicture.height());
		expectedPictureAfterCarving.set(0, 0, new Color(216));
		expectedPictureAfterCarving.set(0, 1, new Color(213));
		expectedPictureAfterCarving.set(0, 2, new Color(214));
		expectedPictureAfterCarving.set(0, 3, new Color(219));

		expectedPictureAfterCarving.set(1, 0, new Color(220));
		expectedPictureAfterCarving.set(1, 1, new Color(221));
		expectedPictureAfterCarving.set(1, 2, new Color(222));
		expectedPictureAfterCarving.set(1, 3, new Color(223));

		assertEquals(expectedPictureAfterCarving, actualPictureAfterCarving);
	}

	@Test
	public void testTransposePicture() {

		Picture picture = setPicture();

		SeamCarver seamCarver = new SeamCarver(picture);
		//Picture transposedPicture = seamCarver.transpose(picture);

		for (int i = 0; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				//assertEquals(picture.get(i, j), transposedPicture.get(j, i));
			}
		}
	}

	private Picture setPicture() {
		Picture picture = new Picture(3, 4);
		picture.set(0, 0, new Color(212));
		picture.set(0, 1, new Color(213));
		picture.set(0, 2, new Color(214));
		picture.set(0, 3, new Color(215));

		picture.set(1, 0, new Color(216));
		picture.set(1, 1, new Color(217));
		picture.set(1, 2, new Color(218));
		picture.set(1, 3, new Color(219));

		picture.set(2, 0, new Color(220));
		picture.set(2, 1, new Color(221));
		picture.set(2, 2, new Color(222));
		picture.set(2, 3, new Color(223));
		return picture;
	}
	
	@Test
	public void testRemoveHorizontalSeamThenRemoveVerticalSeam() {
		Picture picture = new Picture(3, 4);
		SeamCarver sc = new SeamCarver(picture);
		sc.removeHorizontalSeam(sc.findHorizontalSeam());
		sc.removeVerticalSeam(sc.findVerticalSeam());
	}
	
	@Test
	public void getRedTest(){
		
	}
}
