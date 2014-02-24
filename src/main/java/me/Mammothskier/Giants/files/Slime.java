package main.java.me.Mammothskier.Giants.files;

public enum Slime {
	SLIME("plugins/Giants/Slime/slime.yml");

	private final String _slimepath;

	private Slime(final String path) {
		_slimepath = path;
	}

	public String getPath() {
		return _slimepath;
	}
}