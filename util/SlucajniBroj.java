package hr.kviz.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SlucajniBroj {
	
	private static final int constM = Integer.MAX_VALUE;
	private static final int constA = 397204094;
	
	private long longIO = 0l; //inicijalni broj
	private BigDecimal bdIN_D_M = BigDecimal.ZERO; // io / m na 6 decimala
	private long longIN_C_M = 0l; // long (in_d_m)
	private long longIN_P_M = 0l; // in_c_m * m
	
	public SlucajniBroj() {
		super();
		init();
	}
	
	@Override
	public String toString() {
		return "SlucajniBroj [longIO=" + longIO + ", bdIN_D_M=" + bdIN_D_M + ", longIN_C_M=" + longIN_C_M
				+ ", longIN_P_M=" + longIN_P_M + "]";
	}

	private void init() {
		GregorianCalendar gc = new GregorianCalendar();
		int milisekunde = gc.get(Calendar.MILLISECOND);
		int sekunde = gc.get(Calendar.SECOND);
		int io = (sekunde * 1000) + milisekunde;
		longIO = (long) io;
		return;
	}
	
	public int nextInt(int maxVrj) {
		BigDecimal bd = nextBD();
		double broj = (bd.doubleValue() * (double) maxVrj) + 1;
		return (int) broj;
	}

	public BigDecimal nextBD() {
		longIO *= constA;
		bdIN_D_M = podijeli(longIO, constM, 6);
		longIN_C_M = bdIN_D_M.longValue();
		longIN_P_M = longIN_C_M * constM;
		longIO -= longIN_P_M;
		BigDecimal bd = podijeli(longIO, constM, 13);
		return bd;
	}

	private BigDecimal podijeli(long lbrj, int ibrj, int decimala) {
		BigDecimal bd = new BigDecimal(lbrj);
		bd = bd.divide(new BigDecimal(ibrj), decimala, RoundingMode.HALF_UP);
		return bd;
	}
		
}
