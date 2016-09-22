package dang.JavaAdvance.job.polymorp.interImpl;

import dang.JavaAdvance.job.polymorp.inter.Instrument;
import dang.JavaAdvance.job.polymorp.inter.Note;

public class Piano implements Instrument {

	public void play(Note note) {
		// TODO Auto-generated method stub
		System.out.println("Piano play " + note);
	}

}
