package pcol.client.config;

import pcol.client.materii.EditDescrierePlace;
import pcol.client.materii.EditMaterialePlace;
import pcol.client.materii.ProfOverviewPlace;
import pcol.client.schedule.SchedulePlace;
import pcol.client.teme.EditTemePlace;
import pcol.client.tweet.SendTweetPlace;

public class ProfPlaceConfig extends PlaceConfigBase {
	public ProfPlaceConfig() {
		tokenizers.put("compune", new SendTweetPlace.Tokenizer());
		placetokens.put(SendTweetPlace.class, "compune");
		
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
