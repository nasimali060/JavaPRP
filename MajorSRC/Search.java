import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;

public class Search {
	
	private ArrayList <Ping> sharkPings;
	private TreeMap <String, String>sortedSharks;
	private ConcurrentHashMap <String, String>removedDuplicates;
	private TreeMap <String, Shark>reSortedUniqueSharks; 
	protected TreeMap <String, Shark>finalSharkResults;
	
	private String trackingRangeInput;
	private String genderInput;
	private String stageOfLifeInput;
	private String tagLocationInput;
	private Jaws jaws;
	
	public Search(String trackingRangeInput, String genderInput, String stageOfLife, String tagLocation, Jaws jaw){
		this.trackingRangeInput = trackingRangeInput;
		this.genderInput = genderInput;
		this.stageOfLifeInput = stageOfLife;
		this.tagLocationInput = tagLocation;	
		this.jaws = jaw;
		searchTimeRange();
		removeDuplicates();
		verificationByFilter();
		
	}
	
	private void searchTimeRange(){
		if(trackingRangeInput.equals("Past 24 Hours")){
			 sharkPings = jaws.past24Hours();
		}
		 else if(trackingRangeInput.equals("Past Week")){
			 sharkPings = jaws.pastWeek();
		}
		 else if(trackingRangeInput.equals("Past Month")){
			 sharkPings = jaws.pastMonth();
		}
		
	}
	
	private void removeDuplicates(){
		sortedSharks = new TreeMap<>();
		removedDuplicates = new ConcurrentHashMap<>();
		reSortedUniqueSharks = new TreeMap<>();
			for (Ping ping: sharkPings){
				sortedSharks.put(ping.getTime(), ping.getName());
			}
			for (String date: sortedSharks.keySet()){
				removedDuplicates.put(sortedSharks.get(date), date);
			}
			for (String shark:removedDuplicates.keySet()){
				reSortedUniqueSharks.put(removedDuplicates.get(shark), jaws.getShark(shark));
			}
	}
	
	private void verificationByFilter(){
		finalSharkResults = new TreeMap<>(Collections.reverseOrder());
		for(String date: reSortedUniqueSharks.keySet()){
			if (genderInput.equals(reSortedUniqueSharks.get(date).getGender())){
				if (stageOfLifeInput.equals(reSortedUniqueSharks.get(date).getStageOfLife())){
					if (tagLocationInput.equals(reSortedUniqueSharks.get(date).getTagLocation())){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
					else if (tagLocationInput.equals("All")){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
				} else if (stageOfLifeInput.equals("All")){
					if (tagLocationInput.equals(reSortedUniqueSharks.get(date).getTagLocation())){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
					else if (tagLocationInput.equals("All")){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
				}
			}
			else if (genderInput.equals("All")){
				if (stageOfLifeInput.equals(reSortedUniqueSharks.get(date).getStageOfLife())){
					if (tagLocationInput.equals(reSortedUniqueSharks.get(date).getTagLocation())){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
					else if (tagLocationInput.equals("All")){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
				} else if (stageOfLifeInput.equals("All")){
					if (tagLocationInput.equals(reSortedUniqueSharks.get(date).getTagLocation())){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
					else if (tagLocationInput.equals("All")){
						finalSharkResults.put(date, reSortedUniqueSharks.get(date));
					}
				}
			}
		}
	}

}
