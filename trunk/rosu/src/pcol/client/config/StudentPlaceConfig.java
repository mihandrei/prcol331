package pcol.client.config;

import pcol.client.contract.ContractPlace;
import pcol.client.materii.MateriePlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.TemePlace;
import pcol.client.tweet.TweetPlace;


public class StudentPlaceConfig extends PlaceConfigBase {
	public StudentPlaceConfig() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("contract", new ContractPlace.Tokenizer());
		placetokens.put(ContractPlace.class, "contract");
		
		tokenizers.put("teme", new TemePlace.Tokenizer());
		placetokens.put(TemePlace.class, "teme");
		
		tokenizers.put("materie", new MateriePlace.Tokenizer());
		placetokens.put(MateriePlace.class, "materie");
		
		tokenizers.put("teme", new TemePlace.Tokenizer());
		placetokens.put(TemePlace.class, "teme");
		
		tokenizers.put("orar", new SchedulePlace.Tokenizer());
		placetokens.put(SchedulePlace.class, "orar");
	}
}
