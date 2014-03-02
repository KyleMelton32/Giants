package me.Mammothskier.Giants.files;

public enum GiantBiomes {
	GIANTBIOMES("plugins/Giants/Giant/biomes.yml");

	private final String _giantbiomepath;

	private GiantBiomes(final String path) {
		_giantbiomepath = path;
	}

	public String getPath() {
		return _giantbiomepath;
	}
}
