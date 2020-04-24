import java.util.ArrayList;

public class Operations {
	
	public static void main(String[] args) {
		
		/*
		 * These are testing inputs
		 */
		ArrayList<String> nums = new ArrayList<String>();
		nums.add("6");
		nums.add("5");
		nums.add("2");
		nums.add("3");
		System.out.println(derivative(new String[]{"2", "2", "2", "2", "2", "2"}));
		System.out.println(antiderivative(new String[]{"2", "2", "2", "2", "2", "2"}));
		System.out.println(integral(new String[]{"2", "2", "2", "2", "2", "2"}, new String[]{"2", "4"}));
		System.out.println(solve(nums, "+*^"));
		
	}
	
	public static String derivative(String[] coefficients) {
		
		double[] co = new double[6];
		double[] newco = new double[5];
		
		for (int i = 0; i < coefficients.length; i++) {
			co[i] = Double.parseDouble(coefficients[i]);
			if (i < 5) {
				newco[i] = co[i]*(5-i);
			}
		}
		
		String result = String.format("%fx^4 + %fx^3 + %fx^2 + %fx + %f", newco[0], newco[1], newco[2], newco[3], newco[4]);
		return result;
	}
	
	public static String integral(String[] coefficients, String[] endPoints) {
		
		double[] co = new double[6];
		double[] newco = new double[6];
		double start = Double.parseDouble(endPoints[0]);
		double end = Double.parseDouble(endPoints[1]);
		
		for (int i = 0; i < coefficients.length; i++) {
			co[i] = Double.parseDouble(coefficients[i]);
			newco[i] = co[i]/(6-i);
		}
		
		double integral = valueOfAt(newco, end) - valueOfAt(newco, start);
		String result = String.format("%f", integral);
		return result;
		
	}
	
	public static String antiderivative(String[] coefficients) {
		
		double[] co = new double[6];
		double[] newco = new double[6];
		
		for (int i = 0; i < coefficients.length; i++) {
			co[i] = Double.parseDouble(coefficients[i]);
			newco[i] = co[i]/(6-i);
		}
		
		String result = String.format("%fx^6 + %fx^5 + %fx^4 + %fx^3 + %fx^2 + %fx + C", newco[0], newco[1], newco[2], newco[3], newco[4], newco[5]);
		return result;
		
	}

	public static String solve(ArrayList<String> numbers, String operation) {
	
		if (numbers.size() == operation.length()) {
			return "Input Error";
		}
		
		String answer;
		ArrayList<String> op = new ArrayList<String>();
		ArrayList<String> num = new ArrayList<String>();
		
		for (int i = 0; i <= operation.length(); i++) {
			if (i != operation.length()) {
				op.add(String.valueOf(operation.charAt(i)));
			}
			num.add(numbers.get(i));
		}
		 for (int e = 0; e <= 4; e++) {
			String important;
			if (e == 0) {
				important = "^";
			} else if (e == 1) {
				important = "*";
			} else if (e == 2) {
				important = "/";
			} else if (e == 3) {
				important = "+";
			} else {
				important = "-";
			}
			
			for (int s = 0; s < op.size(); s++) {
				if (op.get(s).contentEquals(important)) {
					System.out.println(num.get(s) + important + num.get(s+1));
					num.set(s, String.format("%f", arithmetic(important, Double.parseDouble(num.get(s)), Double.parseDouble(num.get(s+1)))));
					num.remove(s+1);
					op.remove(s);
				}
			}
			
		}
		
		
		answer = num.get(0);
		
		
		return answer;
	
	}
	
	public static double arithmetic(String op, double n1, double n2) {
		
		if (op.equals("^")) {
			return Math.pow(n1, n2);
		} else if (op.equals("*")) {
			return n1*n2;
		} else if (op.equals("/")) {
			return n1/n2;
		} else if (op.equals("+")) {
			return n1+n2;
		} else {
			return n1-n2;
		}
		
	}
	
	public static double valueOfAt(double[] f, double x) {
		
		double val = 0;
		
		for (int i = 0; i < f.length; i++) {
			val += f[i]*Math.pow(x, f.length - i);
		}
		
		return val;
		
	}
	
}
