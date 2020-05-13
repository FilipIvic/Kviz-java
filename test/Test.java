package hr.kviz.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import hr.kviz.dto.PitanjeCompareDto;
import hr.kviz.manager.PitanjeManager;
import hr.kviz.util.SlucajniBroj;

public class Test {

	public static void main(String[] args) {
		try {
			
			Test t = new Test();
			
			int maxInt = 1024; //Integer.MAX_VALUE;
			t.testPrvoPonavljanje(maxInt);
			//t.testBase64();
			
			//t.svaPitanja();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.exit(0);
	}

	private void svaPitanja() {
		try {
			PitanjeManager manager = new PitanjeManager();
			List<PitanjeCompareDto> lista = manager.dajSvaPitanjaSaOdgovorima();
			for (PitanjeCompareDto dto : lista) {
				System.out.println(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private void testBase64() {
		// TODO Auto-generated method stub
		byte[] encodedBytes = Base64.getEncoder().encode("admin".getBytes());
		System.out.println("encodedBytes " + new String(encodedBytes));
		byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));		
		
		char[] charArray1 = new char[]{'d','h','r','f'};
	    char[] charArray2 = new char[]{'a','h','r','f'};
	    
	    boolean blnResult = Arrays.equals(charArray1,charArray2);
	    System.out.println("Are two char arrays equal ? : " + blnResult);
		
		return;
	}

	private void testPrvoPonavljanje(int maxInt) {

		SlucajniBroj slbroj = new SlucajniBroj();
		
		List<Integer> listaMoje = new ArrayList<Integer>();
		int rbr = 0;
		boolean test = true;
		while (test == true) {
			rbr++;
			Integer ibrj = new Integer(slbroj.nextInt(maxInt));
			if (listaMoje.contains(ibrj)) {
				test = false;
			} 
			listaMoje.add(ibrj);
		}
		System.out.println("Prvo moje ponavljanje nakon " + rbr + " pokušaja: ");
		//System.out.println(listaMoje);
		
		Random rand = new Random();
		List<Integer> listaJava = new ArrayList<Integer>();
		rbr = 0;
		test = true;
		while (test == true) {
			rbr++;
			Integer ibrj = new Integer(rand.nextInt(maxInt));
			if (listaJava.contains(ibrj)) {
				test = false;
			} 
			listaJava.add(ibrj);
		}
		System.out.println("Prvo Java ponavljanje nakon " + rbr + " pokušaja.");
		//System.out.println(listaJava);
		
		return;
	}

	
	
}
