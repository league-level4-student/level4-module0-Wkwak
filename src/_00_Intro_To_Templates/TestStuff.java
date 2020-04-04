package _00_Intro_To_Templates;

public class TestStuff {
		
	public static void main(String[] args) {
		int x = 3;
		
		switch(x) {
		case 0: 
			System.out.println("this");
			break;
	
		case 1:
			System.out.println("this");
			break;
			
		case 2:
			System.out.println("no");
			break;
		
		default:
			System.out.println("yestadga");
			break;
		}
	}
	
	
}
	class otherClass<T>{
		T template; 
		
		public otherClass(T template2){
			this.template = template2;
		}
	}
