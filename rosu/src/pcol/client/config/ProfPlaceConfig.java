package pcol.client.config;

import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminPlace;

public class ProfPlaceConfig extends PlaceConfigBase {
	public ProfPlaceConfig() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("usradmin", new UsrAdminPlace.Tokenizer());
		placetokens.put(UsrAdminPlace.class, "usradmin");
	}
}
