package main.java.me.Mammothskier.Giants.files;

public enum MagmaCube {
	MAGMACUBE("plugins/Giants/Magma Cube/magmacube.yml");

	private final String _magmacubepath;

	private MagmaCube(final String path) {
		_magmacubepath = path;
	}

	public String getPath() {
		return _magmacubepath;
	}
}
