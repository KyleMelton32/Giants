package main.java.me.Mammothskier.Giants.files;

public enum SlimeBiomes {
	SLIMEBIOMES("plugins/Giants/Slime/biomes.yml");

	private final String _slimebiomepath;

	private SlimeBiomes(final String path) {
		_slimebiomepath = path;
	}

	public String getPath() {
		return _slimebiomepath;
	}
}
