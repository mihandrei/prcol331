package pcol.client.config;

import pcol.client.useradmin.UsrAdminPlace;

public class AdminPlaceConfig extends PlaceConfigBase {
	public AdminPlaceConfig() {
		tokenizers.put("usradmin", new UsrAdminPlace.Tokenizer());
		placetokens.put(UsrAdminPlace.class, "usradmin");
	}
}
