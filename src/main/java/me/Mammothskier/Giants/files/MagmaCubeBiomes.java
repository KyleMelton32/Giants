package me.Mammothskier.Giants.files;

public enum MagmaCubeBiomes {
	MAGMACUBEBIOMES("plugins/Giants/Magma Cube/biomes.yml");

	private final String _magmacubebiomepath;

	private MagmaCubeBiomes(final String path) {
		_magmacubebiomepath = path;
	}

	public String getPath() {
		return _magmacubebiomepath;
	}
}
