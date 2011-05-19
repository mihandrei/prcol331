package pcol.client.config;

import pcol.client.contract.ContractPlace;
import pcol.client.materii.StudentOverviewPlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.StudentOverViewPlace;
import pcol.client.tweet.TweetPlace;


public class StudentPlaceConfig extends PlaceConfigBase {
	public StudentPlaceConfig() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("contract", new ContractPlace.Tokenizer());
		placetokens.put(ContractPlace.class, "contract");
		
		tokenizers.put("teme", new StudentOverViewPlace.Tokenizer());
		placetokens.put(StudentOverViewPlace.class, "teme");
		
		tokenizers.put("materie", new StudentOverviewPlace.Tokenizer());
		placetokens.put(StudentOverviewPlace.class, "materie");
		
		tokenizers.put("teme", new StudentOverViewPlace.Tokenizer());
		placetokens.put(StudentOverViewPlace.class, "teme");
		
		tokenizers.put("orar", new SchedulePlace.Tokenizer());
		placetokens.put(SchedulePlace.class, "orar");
	}
}
