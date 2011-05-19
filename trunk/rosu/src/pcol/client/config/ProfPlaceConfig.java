package pcol.client.config;

import pcol.client.materii.EditDescrierePlace;
import pcol.client.materii.EditMaterialePlace;
import pcol.client.materii.ProfOverviewPlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.EditTemePlace;
import pcol.client.tweet.TweetPlace;

public class ProfPlaceConfig extends PlaceConfigBase {
	public ProfPlaceConfig() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("orar", new SchedulePlace.Tokenizer());
		placetokens.put(SchedulePlace.class, "orar");
		
		tokenizers.put("materie", new ProfOverviewPlace.Tokenizer());
		placetokens.put(ProfOverviewPlace.class, "materie");
		
		tokenizers.put("editdescriere", new EditDescrierePlace.Tokenizer());
		placetokens.put(EditDescrierePlace.class, "editdescriere");
		
		tokenizers.put("editmateriale", new EditMaterialePlace.Tokenizer());
		placetokens.put(EditMaterialePlace.class, "editmateriale");
		
		tokenizers.put("editteme", new EditTemePlace.Tokenizer());
		placetokens.put(EditTemePlace.class, "editteme");
	}
}
