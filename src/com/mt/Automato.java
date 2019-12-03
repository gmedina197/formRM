package com.mt;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Automato {
	int numStates;
	int initialState;
	ArrayList<String> convertedStates;
	
	List<String> alphabet;
	Map<String, String> convertedAlphabet;
	
	String finalStates;
	
	Map<String, String> convertedDirections;
	
	ArrayList<Transition> transitions;
	
	public Automato(int _numStates, List<String> _alphabet, ArrayList<String> _transitions, int _initialState, List<String> finalStatesArray) {
		this.numStates = _numStates;
		this.initialState = _initialState;
		this.alphabet = _alphabet;
		
		this.finalStates = this.makeFinalStateString(finalStatesArray);
		this.convertedStates = this.makeStatesArray();
		this.convertedAlphabet = this.makeAlphabetArray();
		this.convertedDirections = this.makeDirectionsArray();
		
		this.transitions = this.makeTransitionsArray(_transitions);
		
		System.out.println(this.convertedStates);
		System.out.println(this.convertedAlphabet);
		System.out.println(this.convertedDirections);
		System.out.println(this.transitions);
	}

	public void show() {
		for(String state: this.convertedStates) {
			System.out.println(state);
		}
		
		for(Map.Entry<String, String> entry : this.convertedAlphabet.entrySet()) {
		    String value = entry.getValue();
		    System.out.println(value);
		}
		
		for(Map.Entry<String, String> entry : this.convertedDirections.entrySet()) {
		    String value = entry.getValue();
		    System.out.println(value);
		}
		
		String repRM = this.finalStates + "00";
		for(Transition transition: this.transitions) {
			repRM = repRM + transition.translatedTransition + "00";
			System.out.println(transition.translatedTransition);
		}
		
		System.out.println(this.finalStates);
		
		System.out.println(repRM);
		
	}
	
	private String makeFinalStateString(List<String> finalStateArray) {
		String converted = "";
		int idx = 0;
		for(String finalState: finalStateArray) {
			String convertedState = "";
			for (int i = 0; i < Integer.parseInt(finalState); i++) {
				convertedState = convertedState + "1";
			}
			
			converted = converted + convertedState;
			
			if(idx > 0 && idx < finalStateArray.size()) {
				converted = converted + "0";
			}
			
			convertedState = "";
			idx++;
		}
		
		return converted;
	}
	
	private ArrayList<Transition> makeTransitionsArray(ArrayList<String> transitions) {
		ArrayList<Transition> array = new ArrayList<>();
		
		for(String transition: transitions) {
			String[] parts = transition.split(" ");
			
			Transition newTrasition = new Transition(
					parts[0],
					parts[1],
					parts[2],
					parts[3],
					parts[4]
			);
			
			newTrasition.translatedTransition = 
					this.convertedStates.get(Integer.parseInt(newTrasition.qi) - 1) + "0" +
					this.convertedAlphabet.get(newTrasition.x) + "0" + 
					this.convertedStates.get(Integer.parseInt(newTrasition.qj) - 1) + "0" + 
					this.convertedAlphabet.get(newTrasition.y) + "0" +
					this.convertedDirections.get(newTrasition.dir);
					
			System.out.println(newTrasition.translatedTransition);
			array.add(newTrasition);
		}
		
		return array;
	}
	
	private Map<String,String> makeDirectionsArray() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("D", "1");
		map.put("E", "11");
		
		return map;
	}
	
	private Map<String, String> makeAlphabetArray() {
		Map<String, String> map = new HashMap<String, String>();
		String translateTo = "";
		
		for (int i = 0; i < this.alphabet.size(); i++) {
			for (int j = 0; j < i+1; j++) {
				translateTo = translateTo + "1";
			}
			
			map.put( this.alphabet.get(i), translateTo);
			translateTo = "";
		}
		
		return map;
	}
	
	private ArrayList<String> makeStatesArray() {
		ArrayList<String> array = new ArrayList<>();
		String state = "";
		for (int i = 0; i < this.numStates; i++) {
			for (int j = 0; j < i+1; j++) {
				//System.out.print("1");
				state = state + "1";
			}
			array.add(state);
			state = "";
		}
		
		return array;
	}
}
