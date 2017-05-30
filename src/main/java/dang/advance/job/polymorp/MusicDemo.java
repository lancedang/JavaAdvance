package dang.advance.job.polymorp;

import dang.advance.job.polymorp.inter.Instrument;
import dang.advance.job.polymorp.inter.Note;
import dang.advance.job.polymorp.interImpl.Wind;

public class MusicDemo {

	public static void tune(Instrument instrument, Note note) {
		instrument.play(note);
	}

	public static void main(String[] args) {
		tune(new Wind(), Note.NOTE_ONE);
	}
}
