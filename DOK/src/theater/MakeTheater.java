package theater;

public class MakeTheater {	
	
	String[] area = {"����","���","����/��û/����","�λ�/�뱸/���"};
	String[] s1 = {"����","ȫ��","����","����"};
	String[] s2 = {"����","����","����","�Ǳ�"};
	String[] s3 = {"����","���","����"};
	String[] s4 = {"�뱸","�λ��","���"};
			
	int numHall = 5;
	
	public MakeTheater() {
		DB_Theater connect = new DB_Theater();
		
		//����
		for(int i=0; i<3; i++) {	
			System.out.println(connect.makeMovie_Theater(area[0], s1[i], 5));
		}
		//���
		for(int i=0; i<3; i++) {	
			System.out.println(connect.makeMovie_Theater(area[1], s2[i], 5));
		}
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MakeTheater();
	}

}
