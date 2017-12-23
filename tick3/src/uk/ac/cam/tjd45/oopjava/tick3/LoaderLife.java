package uk.ac.cam.tjd45.oopjava.tick3; //TODO: replace your-crsid

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;

public class LoaderLife {

	/*
	 * public static void main(String[] args) throws java.io.IOException { try {
	 * if (args.length == 0) throw new PatternFormatException(
	 * "Please specify a pattern."); Pattern p = new Pattern(args[0]);
	 * TestArrayWorld world = new TestArrayWorld(p.getWidth(),p.getHeight());
	 * p.initialise(world); play(world); } catch (PatternFormatException e) {
	 * System.out.println(e.getMessage()); }
	 * 
	 * }
	 */

	public static void main(String[] args) throws java.io.IOException {

		if (args.length != 1 && args.length != 2 && args.length != 3) {
			System.out.println("LoaderLife [optional worldType] [patternSource] [option patternIndex]");
			return;
		}

		if (args.length == 1) {
			if (args[0].equals("--array") | args[0].equals("--aging") | args[0].equals("--long")) {
				System.out.println("Please specify a pattern source.");
				return;
			}
		}

		int i = 0;
		List<Pattern> patternlist = null;
		int numofargs = args.length;
		String worldType = "";
		Pattern target = null;
		int patternsource = 0;

		if (numofargs == 3) {
			patternsource = 1;
		}

		if (args[patternsource].startsWith("http://")) {
			try {
				patternlist = PatternLoader.loadFromURL(args[patternsource]);
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred loading from URL: " + args[patternsource]);
				return;
			}
		} else {
			try {
				patternlist = PatternLoader.loadFromDisk(args[patternsource]);
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred loading from file: " + args[patternsource]);
				return;
			}
		}

		if (numofargs == 2) {
			worldType = "--array";
		} else {
			if (numofargs == 3) {
				worldType = args[0];
			}
		}

		if (numofargs == 1) {
			for (Pattern p : patternlist) {
				System.out.println(i + "\t" + p.getName() + "\t" + p.getAuthor());
				i++;
			}
		} else {
			int j;

			try {
				j = Integer.parseInt(args[numofargs - 1]);

			} catch (NumberFormatException e) {
				System.out.println(
						"Could not interpret patternIndex as an integer (given '" + args[numofargs - 1] + "').");
				return;
			}

			if (j > patternlist.size() - 1) {
				System.out.println("Chosen index number not present in list.");
				return;
			}

			target = patternlist.get(j);

			try { // Not sure this needs to surround the whole thing anymore

				World world = null;
				if (worldType.equals("--array")) {
					world = new ArrayWorld(target.getWidth(), target.getHeight());
				} else

				if (worldType.equals("--long")) {
					world = new PackedWorld();
				} else

				if (worldType.equals("--aging")) {
					world = new AgingWorld(target.getWidth(), target.getHeight());
				} else {
					System.out.println(
							"You have not specified a valid type of world. Expected '--array', '--long' or '--aging'");
					return;
				}

				target.initialise(world);
				play(world);
			} catch (PatternFormatException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	public static void play(World world) throws java.io.IOException {
		int userResponse = 0;
		WorldViewer viewer = new WorldViewer();
		while (userResponse != 'q') {

			Writer w = new OutputStreamWriter(System.out);
			world.print(w);
			viewer.show(world);
			userResponse = System.in.read();

			world = world.nextGeneration(0);

		}
		viewer.close();
	}

}