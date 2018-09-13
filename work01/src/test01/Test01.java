package test01;

import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {
	public static void main(String []args) {
		int no = 1;
		ArrayList<Score> list = new ArrayList<Score>();
		Scanner scanf = new Scanner(System.in);
		System.out.println("*** �� �� �� �� �� �� �� �� �� ***");
		while(true) {
			int num = menu(scanf);
			if(num >= 1 && num <= 4) {
				switch(num) {
					case 1: check(list); break;
					case 2: if(no > 10) {System.out.println("�Է� ������ �л��� ���� �ʰ��Ͽ����ϴ�.");} else {Score s = input(scanf, no); list.add(s); no++;}; break;
					case 3: rank(list); break;
					case 4: list.clear(); System.out.println("�Է� ������ �ʱ�ȭ �Ǿ����ϴ�."); no = 1; break;
				}
			} else if(num == 5) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			} else {
				System.out.println("�������� �ʴ� �޴��Դϴ�.");
			}
		}
	}
	
	public static int menu(Scanner scanf) {
		System.out.println("�޴��� �����ϼ���. (1. ��ȸ / 2. �Է� / 3. ����/ 4. �ʱ�ȭ/ 5. ����)");
		int num = scanf.nextInt();
		return num;
	}
	
	public static void check(ArrayList<Score> list) {
		if(list.isEmpty()) {
			System.out.println("�Էµ� ������ �����ϴ�.");
		} else {
			System.out.println("��ȣ\t����\t����\t����\t���");
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getNo()+"\t"+list.get(i).getKor()+"\t"+list.get(i).getMath()+"\t"+list.get(i).getEng()+"\t"+list.get(i).getAvg());
			}
		}
		return;
	}
	
	public static Score input(Scanner scanf, int no) {
		Score s = new Score();
		System.out.println(no + "�� �л��� ���� ���� : ");
		double kor = scanf.nextDouble();
		System.out.println(no + "�� �л��� ���� ���� : ");
		double math = scanf.nextDouble();
		System.out.println(no + "�� �л��� ���� ���� : ");
		double eng = scanf.nextDouble();
		s.setNo(no);
		s.setKor(kor);
		s.setMath(math);
		s.setEng(eng);
		s.setAvg((kor + math + eng)/3);
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public static void rank(ArrayList<Score> list) {
		ArrayList<Score> listt = (ArrayList<Score>) list.clone();
		if(list.isEmpty()) {
			System.out.println("�Էµ� ������ �����ϴ�.");
		} else {
			System.out.println("����\t��ȣ\t���");
			for(int i = 0; i < listt.size() - 1; i++) {
				for(int j = i + 1; j < listt.size(); j++) {
					if(listt.get(j).getAvg() > listt.get(i).getAvg()) {
						Score temp = listt.get(j);
						listt.set(j, listt.get(i));
						listt.set(i, temp);
					}
				}
			}

			for(int i = 0; i < listt.size(); i++) {
				System.out.println((i+1) + "\t" + listt.get(i).getNo() + "\t" + listt.get(i).getAvg());
			}
		}
		return;
	}
}
