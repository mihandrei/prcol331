package pcol.client.config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import pcol.client.tweet.TweetPlace;
import pcol.client.useradmin.UsrAdminPlace;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Clasa de config . Ar putea fi generata dintr-un config file;
 * Ocoleste mecanismul implicit din gwt 
 * @see StudentTabConfig
 */
public class AdminPlaceConfig implements PlaceHistoryMapper {
	private static Logger log = Logger.getLogger(AdminPlaceConfig.class.getName());

	private final Map<String, PlaceTokenizer<?>> tokenizers = new HashMap<String, PlaceTokenizer<?>>();
	private final Map<Class<? extends Place>, String> placetokens = new HashMap<Class<? extends Place>, String>();

	public AdminPlaceConfig() {
		tokenizers.put("noutati", new TweetPlace.Tokenizer());
		placetokens.put(TweetPlace.class, "noutati");
		
		tokenizers.put("usradmin", new UsrAdminPlace.Tokenizer());
		placetokens.put(UsrAdminPlace.class, "usradmin");
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
			log.warning("no place for token " + token);
			return null;
		} else {
			return tokenizer.getPlace(placeArgs);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
