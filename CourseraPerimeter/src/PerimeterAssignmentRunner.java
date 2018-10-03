import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
	public double getPerimeter(Shape s) {
		// Start with totalPerim = 0
		double totalPerim = 0.0;
		// Start wth prevPt = the last point
		Point prevPt = s.getLastPoint();
		// For each point currPt in the shape,
		for (Point currPt : s.getPoints()) {
			// Find distance from prevPt point to currPt
			double currDist = prevPt.distance(currPt);
			// Update totalPerim by currDist
			totalPerim = totalPerim + currDist;
			// Update prevPt to be currPt
			prevPt = currPt;
		}
		// totalPerim is the answer
		return totalPerim;
	}

	public int getNumPoints(Shape s) {
		int count = 0;
		for (Point p : s.getPoints()) {
			count += 1;
		}
		return count;
	}

	public double getAverageLength(Shape s) {
		/*Point lastPoint = s.getLastPoint();
		double totalLength = 0.0;
		int count = 0;
		for (Point currentPoint : s.getPoints()) {
			totalLength += currentPoint.distance(lastPoint);
			lastPoint = currentPoint;
			count++;
		}
		double averageLength = totalLength / count;*/
		PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
		double averageLength = pr.getPerimeter(s)/pr.getNumPoints(s);
		return averageLength;
	}

	public double getLargestSide(Shape s) {
		double largestSide=0.0;
        Point lastPt = s.getLastPoint();
        for (Point p : s.getPoints()) {
        	double distance =p.distance(lastPt); 
            if(distance>largestSide){
                largestSide=distance;
            }
            lastPt = p;
        }
        return largestSide;
	}

	public double getLargestX(Shape s) {
		// Put code here
		return 0.0;
	}

	public double getLargestPerimeterMultipleFiles() {
		// Put code here
		return 0.0;
	}

	public String getFileWithLargestPerimeter() {
		// Put code here
		File temp = null; // replace this code
		return temp.getName();
	}

	public void testPerimeter() {
		FileResource fr = new FileResource();
		Shape s = new Shape(fr);
		double length = getPerimeter(s);
		System.out.println("perimeter = " + length);
		System.out.println("number of points =" + getNumPoints(s));
		System.out.println("largest side = "+ getLargestSide(s));

	}

	public void testPerimeterMultipleFiles() {
		// Put code here
	}

	public void testFileWithLargestPerimeter() {
		// Put code here
	}

	// This method creates a triangle that you can use to test your other methods
	public void triangle() {
		Shape triangle = new Shape();
		triangle.addPoint(new Point(0, 0));
		triangle.addPoint(new Point(6, 0));
		triangle.addPoint(new Point(3, 6));
		for (Point p : triangle.getPoints()) {
			System.out.println(p);
		}
		double peri = getPerimeter(triangle);
		System.out.println("perimeter = " + peri);
	}

	// This method prints names of all files in a chosen folder that you can use to
	// test your other methods
	public void printFileNames() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			System.out.println(f);
		}
	}

	public static void main(String[] args) {
		PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
		//pr.testPerimeter();
		System.out.println(pr.getPerimeter(pr.mySquare()));
		System.out.println(pr.getNumPoints(pr.mySquare()));
		//System.out.println(pr.getAverageLength(pr.mySquare()));
		System.out.println(pr.getLargestSide(pr.mySquare()));
		
	}

	public Shape myTriangle() {
		Shape triangle = new Shape();
		triangle.addPoint(new Point(0, 0));
		triangle.addPoint(new Point(6, 0));
		triangle.addPoint(new Point(3, 6));
		return triangle;
	}

	public Shape mySquare() {
		Shape square = new Shape();
		square.addPoint(new Point(0, 0));
		square.addPoint(new Point(0, 3));
		square.addPoint(new Point(10, 3));
		square.addPoint(new Point(10, 0));
		return square;
	}
}
