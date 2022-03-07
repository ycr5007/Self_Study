package reflection;

public class ReflectionTest {

	// 합계를 구하는 재귀함수
	public static int plusReflection(int n) {
		if(n <= 0) {
			return n;
		}else {
			return n += plusReflection(n - 1);
		}
	}
	
	// 곱셈을 구하는 재귀함수(factorial)
	public static int factReflection(int n) {
		if(n <= 1) {
			return n;
		}else {
			return factReflection(n - 1) * n;
		}
	}
	
	public static void main(String[] args) {
		int n = 10;
		System.out.println("1 부터 10 까지의 합계 : " + plusReflection(n));
		System.out.println("1 부터 10 까지의 곱계 (fact) : " + factReflection(n));
	} 
}
