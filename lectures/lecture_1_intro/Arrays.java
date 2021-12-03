class Arrays {
	public static void main(String args[]) {
		/* declare */
		double[] height; // an array of double values
		double[][] weight; // a 2 dimensional double array

		/* instantiate */
		height = new double[10]; // an array of size 10
		weight = new double[10][10]; // an array of size 10x10

		/* declare and instantiate */
		int[] age = {1, 2, 3};
		int[][] distance = {{10, 20, 30}, {4, 5, 6}}; // {{"first row"}{"second row"}}

		/* access and modify value in an array */
		height[2] = 10.2;
		weight[0][1] = 1001.1;

		/* note: a reference type variable not pointing to any object is assigned null */
	}
}