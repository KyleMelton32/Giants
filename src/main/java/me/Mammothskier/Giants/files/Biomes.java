package me.Mammothskier.Giants.files;

public enum Biomes {
	BIOMES("plugins/Giants/biomes.yml");

	private final String _biomepath;

	private Biomes(final String path) {
		_biomepath = path;
	}

	public String getPath() {
		return _biomepath;
	}
}
