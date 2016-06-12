package me.Mammothskier.Giants.Files;

public enum Files {
	CONFIG("plugins/Giants/config.yml"),
	ENTITIES("plugins/Giants/entities.yml"), BIOMES("plugins/Giants/biomes.yml"),
	ATTACKS("plugins/Giants/attacks.yml"),
	JOCKEY("plugins/Giants/Jockey/jockey.yml"), JOCKEYBIOMES("plugins/Giants/Jockey/biomes.yml");
	
	private final String _path;

	private Files(final String path) {
		_path = path;
	}

	public String getPath() {
		return _path;
	}
	
}