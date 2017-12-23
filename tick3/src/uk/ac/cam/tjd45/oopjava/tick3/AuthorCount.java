package uk.ac.cam.tjd45.oopjava.tick3;

public class AuthorCount implements Comparable<AuthorCount> {
	private String author;
	private int count;

	public AuthorCount(String author) {
		this.author = author;
		this.count = 1;
	}

	public void inc() {
		count++;
	}

	@Override
	public int hashCode() {
		return this.author.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean TORF = false;
		if (obj instanceof AuthorCount) {
			if (((AuthorCount) obj).author.equals(this.author)) {
				TORF = true;
			}
		}
		return TORF;
	}

	@Override
	public int compareTo(AuthorCount o) {
		if (this.count < o.count)
			return 1;
		else if (this.count > o.count)
			return -1;
		else
			return this.author.compareTo(o.author);
	}

	@Override
	public String toString() {
		return String.format("%-20s", this.author) + String.format("%3s", this.count);

	}
}