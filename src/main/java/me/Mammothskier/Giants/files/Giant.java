package me.Mammothskier.Giants.files;

public enum Giant {
	GIANT("plugins/Giants/Giant/giant.yml");

	private final String _giantspath;

	private Giant(final String path) {
		_giantspath = path;
	}

	public String getPath() {
		return _giantspath;
	}
}
