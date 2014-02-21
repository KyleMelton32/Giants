package main.java.me.Mammothskier.Giants.files;

public enum Config {
	CONFIG("plugins/Giants/config.yml");

	private final String _path;

	private Config(final String path) {
		_path = path;
	}

	public String getPath() {
		return _path;
	}
}