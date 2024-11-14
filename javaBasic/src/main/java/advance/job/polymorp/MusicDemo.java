package advance.job.polymorp;

import advance.job.polymorp.inter.Instrument;
import advance.job.polymorp.inter.Note;
import advance.job.polymorp.interImpl.Wind;

public class MusicDemo {

	public static void tune(Instrument instrument, Note note) {
		instrument.play(note);
	}

	public static void main(String[] args) {
		tune(new Wind(), Note.NOTE_ONE);
	}
}
