interface FractionADT {
	int ONE_CONSTANT = 1; // attributes are implicitly "public static final"

	public int getNum();
	public int getDenom();
	public void setNum(int iNum);
	public void setDenom(int iDenom);

	public FractionADT add(FractionADT f);
	public FractionADT minus(FractionADT f);
	public FractionADT times(FractionADT f);
	public FractionADT divide(FractionADT f);
	public FractionADT simplify(FractionADT f);
}

class Fraction implements FractionADT {
	// attributes
	private int Num;
	private int Denom;

	// interface methods
	public int getNum() {
		return this.Num;
	}

	public int getDenom() {
		return this.Denom;
	}

	public void setNum(int iNum) {
		this.Num = iNum;
	}

	public void setDenom(int iDenom) {
		this.Denom = iDenom;
	}

	public FractionADT add(FractionADT f) {
		// TODO
	};

	public FractionADT minus(FractionADT f) {
		// TODO
	};

	public FractionADT times(FractionADT f) {
		// TODO
	};

	public FractionADT divide(FractionADT f) {
		// TODO
	};

	public FractionADT simplify(FractionADT f) {
		// TODO
	};

	// constructor 1
	public Fraction() {
		this(1, 1); // returns a fraction of "1/1" if no arguments are given
	}

 	// constructor 2
	public Fraction(int iNum, int iDenom) {
		setNum(iNum);
		setDenom(iDenom);
	} 

	@Override
	public String toString() {
		return Integer.toString(this.Num) + "/" + Integer.toString(this.Denom);
	}
}

public class InterfacesInADT {
	public static void main(String[] args) {
		Fraction exampleFraction = new Fraction(1, 2);
		Fraction exampleFractionNoArgs = new Fraction();

		System.out.println(exampleFraction);
		System.out.println(exampleFractionNoArgs);
	}
}
