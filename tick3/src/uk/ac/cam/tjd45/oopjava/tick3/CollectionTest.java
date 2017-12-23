package uk.ac.cam.tjd45.oopjava.tick3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CollectionTest {
	public static List<AuthorCount> processWithList(List<Pattern> patterns) {
		List<AuthorCount> list = new LinkedList<AuthorCount>();
		for (Pattern p : patterns) {
			String author = p.getAuthor();
			AuthorCount currentauthor = new AuthorCount(author);

			int i = list.indexOf(currentauthor);

			if (i == -1) {
				list.add(currentauthor);
			} else {
				list.get(i).inc();
			}

		}

		Collections.sort(list);
		return list;

	}

	public static List<AuthorCount> processWithMap(List<Pattern> patterns) {
		Map<String, AuthorCount> map = new HashMap<String, AuthorCount>();
		for (Pattern p : patterns) {
			String author = p.getAuthor();

			if (map.containsKey(author)) {
				map.get(author).inc();
			} else {
				map.put(author, new AuthorCount(author));
			}

		}

		ArrayList<AuthorCount> maplist = new ArrayList<AuthorCount>(map.values());
		Collections.sort(maplist);
		return maplist;

	}

	public static void main(String[] args) throws IOException {
		List<Pattern> listfromurl = PatternLoader
				.loadFromURL("http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt");
		List<AuthorCount> authorcountlist = processWithList(listfromurl);

		for (AuthorCount a : authorcountlist) {
			System.out.println(a.toString());
		}
	}
}