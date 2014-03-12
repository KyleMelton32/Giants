package me.Mammothskier.Giants.files;

public enum Files {
	CONFIG("plugins/Giants/config.yml"),
	GIANT("plugins/Giants/Giant/giant.yml"), GIANTBIOMES("plugins/Giants/Giant/giant biomes.yml"),
	MAGMACUBE("plugins/Giants/Magma Cube/magmacube.yml"), MAGMACUBEBIOMES("plugins/Giants/Magma Cube/magma cube biomes.yml"),
	SLIME("plugins/Giants/Slime/slime.yml"), SLIMEBIOMES("plugins/Giants/Slime/slime biomes.yml");
	
	private final String _path;

	private Files(final String path) {
		_path = path;
	}

	public String getPath() {
		return _path;
	}
	
}
