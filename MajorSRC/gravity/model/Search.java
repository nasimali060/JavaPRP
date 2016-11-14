package gravity.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import api.jaws.Jaws;
import api.jaws.Ping;
import api.jaws.Shark;

public class Search {

	private ArrayList<Ping> sharkPings;
	private Map<String, String> sortedSharks;
	private Map<String, String> removedDuplicates;
	private Map<String, Shark> reSortedUniqueSharks; 
	private Map<String, Shark> finalSharkResults;
	private String trackingRangeInput;
	private String genderInput;
	private String stageOfLifeInput;
	private String tagLocationInput;
	private Jaws jaws;
	
	public Search(Jaws jaws){
		
		this.jaws = jaws;
	
	}
	
	public TreeMap<String, Shark> getFinalResults(){
		
		return (TreeMap<String, Shark>) finalSharkResults;
	
	}
	
	public void searchTimeRange(){
		
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
	
	public void removeDuplicates(){
		
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
	
	public void filter(){
		
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
				} 
				
				else if (stageOfLifeInput.equals("All")){
					
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
				} 
				
				else if (stageOfLifeInput.equals("All")){
					
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
	
	public void setTrackingRangeInput(String trackingRangeInput){
			
		this.trackingRangeInput = trackingRangeInput;
	
	}
	
	public void setGenderInput(String genderInput){
	
		this.genderInput = genderInput;
	
	}
	
	public void setStageOfLifeInput(String stageOfLifeInput){
		
		this.stageOfLifeInput = stageOfLifeInput;
	
	}
	
	public void setTagLocationInput(String tagLocationInput){
		
		this.tagLocationInput = tagLocationInput;
	
	}
	
	
}
