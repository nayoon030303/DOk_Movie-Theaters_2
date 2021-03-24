package theater;

public class MakeTheater {	
	
	String[] area = {"서울","경기","대전/충청/세종","부산/대구/경상"};
	String[] s1 = {"강남","홍대","송파","은평"};
	String[] s2 = {"구리","성남","수원","판교"};
	String[] s3 = {"공주","논산","대전"};
	String[] s4 = {"대구","부산대","울산"};
			
	int numHall = 5;
	
	public MakeTheater() {
		DB_Theater connect = new DB_Theater();
		
		//서울
		for(int i=0; i<3; i++) {	
			System.out.println(connect.makeMovie_Theater(area[0], s1[i], 5));
		}
		//경기
		for(int i=0; i<3; i++) {	
			System.out.println(connect.makeMovie_Theater(area[1], s2[i], 5));
		}
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MakeTheater();
	}

}
