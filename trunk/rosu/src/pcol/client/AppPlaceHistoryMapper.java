package pcol.client;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import pcol.client.contract.ContractPlace;
import pcol.client.materii.MateriePlace;
import pcol.client.materii.MateriiPlace;
import pcol.client.tweet.TweetPlace;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Ocoleste mecanismul implicit din gwt (care-i cam magic si nu suporta #token)
 * asociaza manual history tokenruri cu place-uri
 */
public class AppPlaceHistoryMapper implements PlaceHistoryMapper {
	private static Logger log = Logger.getLogger(AppPlaceHistoryMapper.class.getName());

	private final Map<String, PlaceTokenizer<?>> tokenizers = new HashMap<String, PlaceTokenizer<?>>();
	private final Map<Class<? extends Place>, String> placetokens = new HashMap<Class<? extends Place>, String>();

	public AppPlaceHistoryMapper() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("contract", new ContractPlace.Tokenizer());
		placetokens.put(ContractPlace.class, "contract");
		
		tokenizers.put("teme", new TemePlace.Tokenizer());
		placetokens.put(TemePlace.class, "teme");
		
		tokenizers.put("materii", new MateriiPlace.Tokenizer());
		placetokens.put(MateriiPlace.class, "materii");
		
		tokenizers.put("materie", new MateriePlace.Tokenizer());
		placetokens.put(MateriePlace.class, "materie");
	}

	@Override
	public Place getPlace(String token) {
		String placeId;
		String placeArgs;

		int idx = token.indexOf('/');
		if (idx != -1) {
			placeId = token.substring(0, idx);
			placeArgs = token.substring(idx+1);
		} else {
			placeId = token;
			placeArgs = "";
		}

		PlaceTokenizer<?> tokenizer = tokenizers.get(placeId);

		if (tokenizer == null) {
			return null;
		} else {
			return tokenizer.getPlace(placeArgs);
		}
	}

	@Override
	public String getToken(Place place) {
		String token = placetokens.get(place.getClass());		
		PlaceTokenizer tokenizer = tokenizers.get(token);
		String placeArgs = tokenizer.getToken(place);
		if(placeArgs!=null){
			return token + '/' + placeArgs;
		}else{
			return token;
		}
		
	}

}