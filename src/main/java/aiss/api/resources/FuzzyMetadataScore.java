package aiss.api.resources;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.xdrop.fuzzywuzzy.Applicable;
import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyMetadataScore implements Applicable{

	@Override
	public int apply(String s1, String s2) {
		// TODO Auto-generated method stub
		String[] splits = s2.split(",");
		int ratio1 = 0;
		if(splits[0] != null) 
			ratio1 = FuzzySearch.weightedRatio(s1, splits[0]);
		int ratio2 = 0;
		if(splits[1] != null) 
			ratio2 = FuzzySearch.weightedRatio(s1, splits[1]);
		int ratio3 = 0;
		if(splits[2] != null) 
			ratio3 = FuzzySearch.weightedRatio(s1, splits[2]);
		List<Integer> listaratios = Arrays.asList(ratio1, ratio2, ratio3);
		Integer resultado = listaratios.stream().max(Comparator.comparing(y->y)).get();
		return resultado;
	}
	
	

}
